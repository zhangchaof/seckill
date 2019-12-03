package com.learn.seckill.controller;

import com.learn.seckill.entity.SeckillUserEntity;
import com.learn.seckill.service.SeckillUserService;
import com.learn.seckill.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	SeckillUserService userService;
	
	@Autowired
	RedisUtil redisService;
	
    @RequestMapping("/to_list")
    public String list(Model model, SeckillUserEntity user) {
    	model.addAttribute("user", user);
        return "goods_list";
    }
    
}
