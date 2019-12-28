package com.learn.seckill.controller;

import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.dto.SeckillUserVO;
import com.learn.seckill.service.GoodsService;
import com.learn.seckill.service.SeckillUserService;
import com.learn.seckill.utils.RedisConstant;
import com.learn.seckill.utils.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.learn.seckill.utils.RedisConstant.GOODS_EXPIRE;

/**
 * @author chaofan.zhang
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    SeckillUserService userService;

    @Autowired
    RedisUtil redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @ApiOperation(value = "查询商品列表")
    @RequestMapping(value = "/to_list", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, SeckillUserVO user) {
        model.addAttribute("user", user);
        //取缓存
        Object goodList = redisService.get(RedisConstant.GOODS_LIST);
        String html = goodList == null ? null : goodList.toString();
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        IWebContext ctx = new WebContext(request, response,
                request.getServletContext(), request.getLocale(), model.asMap());
        //手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        if (!StringUtils.isEmpty(html)) {
            redisService.set(RedisConstant.GOODS_LIST, html, GOODS_EXPIRE);
        }
        return html;
    }

    @ApiOperation(value = "查询商品详情")
    @RequestMapping(value = "/to_detail/{goodsCode}", produces = "text/html")
    @ResponseBody
    public String detail(HttpServletRequest request, HttpServletResponse response, Model model, SeckillUserVO user,
                         @PathVariable("goodsCode") String goodsCode) {
        model.addAttribute("user", user);
        //取缓存
        Object detailsGoods = redisService.get(RedisConstant.GOODS_DETAILS.concat(goodsCode));
        String html = detailsGoods == null ? null : detailsGoods.toString();
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        //手动渲染
        GoodsVo goods = goodsService.getGoodsVoByGoodsCode(goodsCode);
        model.addAttribute("goods", goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int seckillStatus = 0;
        int remainSeconds = 0;
        //秒杀还没开始，倒计时
        if (now < startAt) {
            seckillStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
            //秒杀已经结束
        } else if (now > endAt) {
            seckillStatus = 2;
            remainSeconds = -1;
        } else {//秒杀进行中
            seckillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("seckillStatus", seckillStatus);
        model.addAttribute("remainSeconds", remainSeconds);

        IWebContext ctx = new WebContext(request, response,
                request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
        if (!StringUtils.isEmpty(html)) {
            redisService.set(RedisConstant.GOODS_DETAILS.concat(goodsCode), html, GOODS_EXPIRE);
        }
        return html;
    }
}
