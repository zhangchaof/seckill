package com.learn.seckill.controller;

import com.learn.seckill.access.AccessLimit;
import com.learn.seckill.config.Init;
import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.dto.OrderVO;
import com.learn.seckill.dto.SeckillOrderVO;
import com.learn.seckill.dto.SeckillUserVO;
import com.learn.seckill.rabbitmq.MQSender;
import com.learn.seckill.rabbitmq.SeckillMessage;
import com.learn.seckill.redis.RedisConstant;
import com.learn.seckill.redis.RedisUtil;
import com.learn.seckill.result.CodeMsg;
import com.learn.seckill.result.Result;
import com.learn.seckill.service.GoodsService;
import com.learn.seckill.service.OrderService;
import com.learn.seckill.service.SeckillService;
import com.learn.seckill.service.SeckillUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;

import static com.learn.seckill.redis.RedisConstant.SECKILL_USER_ORDER;

/**
 * @program: seckill:SeckillController
 * @description:
 * @author: zcf
 * @create: 2019-12-07 16:55
 **/
@Controller
@RequestMapping("/seckill")
@Slf4j
public class SeckillController {
    @Autowired
    SeckillUserService userService;

    @Autowired
    RedisUtil redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    SeckillService seckillService;

    @Autowired
    Init init;

    @Autowired
    MQSender sender;

    @RequestMapping(value = "/do_seckill", method = RequestMethod.POST)
    @ApiOperation(value = "开始秒杀")
    public String list(Model model, SeckillUserVO user,
                       @RequestParam("goodsCode") String goodsCode) {
        model.addAttribute("user", user);
        if (user == null) {
            return "login";
        }
        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsCode(goodsCode);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.SECKILL_OVER.getMsg());
            return "seckill_fail";
        }
        //判断是否已经秒杀到了
        SeckillOrderVO order = seckillService.getSeckillOrderBySeckillUserIdGoodsCode(user.getUserId(), goodsCode);
        if (order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_SECKILL.getMsg());
            return "seckill_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderVO orderInfo = seckillService.seckill(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }

    @RequestMapping(value = "/{path}/seckill", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "开始秒杀(优化)")
    @AccessLimit(seconds = 5, maxCount = 5)
    public Result<Integer> seckill(Model model, SeckillUserVO user, @RequestParam("goodsCode") String goodsCode, @PathVariable("path") String path) {
        model.addAttribute("user", user);
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        //验证path
        boolean check = seckillService.checkPath(user, goodsCode, path);

        if (!check) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }
        //判断库存
        //内存标记，减少redis访问
        boolean over = Init.localOverMap.get(goodsCode);
        if (over) {
            return Result.error(CodeMsg.SECKILL_OVER);
        }
        //预减库存
        long stock = redisService.decr(RedisConstant.SECKILL_GOODS_STOCK.concat(goodsCode), 1);
        if (stock < 0) {
            Init.localOverMap.put(goodsCode, true);
            return Result.error(CodeMsg.SECKILL_OVER);
        }
        //判断是否已经秒杀到了
        SeckillOrderVO order = seckillService.getSeckillOrderBySeckillUserIdGoodsCode(user.getUserId(), goodsCode);
        if (order != null) {
            return Result.error(CodeMsg.REPEATE_SECKILL);
        }
        //减库存 下订单 写入秒杀订单
        SeckillMessage seckillMessage = new SeckillMessage();
        seckillMessage.setGoodsCode(goodsCode);
        seckillMessage.setUser(user);
        sender.sendSeckillMessage(seckillMessage);
        return Result.success(0);
    }

    /**
     * orderId：成功
     * -1：秒杀失败
     * 0： 排队中
     */
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "秒杀结果")
    public Result<Long> seckillResult(Model model, SeckillUserVO user,
                                      @RequestParam("goodsCode") String goodsCode) {
        model.addAttribute("user", user);
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        Long result = seckillService.getSeckillResult(user.getUserId(), goodsCode);
        return Result.success(result);
    }

    /**
     * 回复到秒杀前数据
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "秒杀数据还原,方便重新秒杀")
    public Result<Boolean> reset(Model model) {
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        for (GoodsVo goods : goodsList) {
            goods.setStockCount(10);
            redisService.set(RedisConstant.SECKILL_GOODS_STOCK + goods.getGoodsCode(), 10);
            Init.localOverMap.put(goods.getGoodsCode(), false);
        }
        redisService.delMatch(SECKILL_USER_ORDER);
        redisService.delMatch(RedisConstant.SECKILL_OVER);

        seckillService.reset(goodsList);
        return Result.success(true);
    }

    @RequestMapping(value = "/path", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "秒杀地址隐藏")
    @AccessLimit(seconds = 500, maxCount = 5)
    public Result<String> getSeckillPath(SeckillUserVO user, @RequestParam("goodsCode") String goodsCode, @RequestParam(value = "verifyCode", defaultValue = "0") int verifyCode) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }

        boolean check = seckillService.checkVerifyCode(user, goodsCode, verifyCode);

        if (!check) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }
        String path = seckillService.createSeckillPath(user, goodsCode);
        return Result.success(path);
    }

    @RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> getSeckillVerifyCod(HttpServletResponse response, SeckillUserVO user, @RequestParam("goodsCode") String goodsCode) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        try {
            BufferedImage image = seckillService.createVerifyCode(user, goodsCode);
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            out.flush();
            out.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SECKILL_FAIL);
        }
    }
}
