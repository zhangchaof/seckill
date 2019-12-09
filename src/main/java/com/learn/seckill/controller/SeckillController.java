package com.learn.seckill.controller;

import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.dto.OrderVO;
import com.learn.seckill.dto.SeckillOrderVO;
import com.learn.seckill.dto.SeckillUserVO;
import com.learn.seckill.result.CodeMsg;
import com.learn.seckill.service.GoodsService;
import com.learn.seckill.service.OrderService;
import com.learn.seckill.service.SeckillService;
import com.learn.seckill.service.SeckillUserService;
import com.learn.seckill.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/do_seckill")
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
}
