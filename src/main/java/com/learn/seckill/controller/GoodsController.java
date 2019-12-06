package com.learn.seckill.controller;

import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.dto.SeckillUserVO;
import com.learn.seckill.entity.SeckillUserEntity;
import com.learn.seckill.service.GoodsService;
import com.learn.seckill.service.SeckillUserService;
import com.learn.seckill.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	SeckillUserService userService;
	
	@Autowired
	RedisUtil redisService;

	@Autowired
	GoodsService goodsService;
	
    @RequestMapping("/to_list")
    public String list(Model model, SeckillUserVO user) {
		//查询商品列表
		List<GoodsVo> goodsList = goodsService.listGoodsVo();
		model.addAttribute("goodsList", goodsList);
		return "goods_list";
    }
    
}
