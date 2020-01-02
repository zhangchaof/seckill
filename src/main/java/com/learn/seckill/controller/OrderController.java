package com.learn.seckill.controller;

import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.dto.OrderDetailVo;
import com.learn.seckill.dto.OrderVO;
import com.learn.seckill.dto.SeckillUserVO;
import com.learn.seckill.entity.OrderEntity;
import com.learn.seckill.result.CodeMsg;
import com.learn.seckill.result.Result;
import com.learn.seckill.service.GoodsService;
import com.learn.seckill.service.OrderService;
import com.learn.seckill.service.SeckillService;
import com.learn.seckill.service.SeckillUserService;
import com.learn.seckill.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

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
	
    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, SeckillUserVO user,
									  @RequestParam("orderNo") String orderNo) {
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
		OrderVO order = orderService.getOrderByNo(orderNo);
    	if(order == null) {
    		return Result.error(CodeMsg.ORDER_NOT_EXIST);
    	}
    	String goodsCode = order.getGoodsCode();
		GoodsVo goods = goodsService.getGoodsVoByGoodsCode(goodsCode);
		OrderDetailVo vo = new OrderDetailVo();
    	vo.setOrder(order);
    	vo.setGoods(goods);
    	return Result.success(vo);
    }
    
}
