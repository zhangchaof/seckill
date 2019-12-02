package com.learn.seckill.controller;

import com.learn.seckill.dto.request.LoginReq;
import com.learn.seckill.result.Result;
import com.learn.seckill.service.SeckillUserService;
import com.learn.seckill.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

	@Autowired
	SeckillUserService seckillUserService;
	
	@Autowired
    RedisUtil redisService;
	
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }
    
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginReq req) {
    	log.info(req.toString());
    	//登录
    	seckillUserService.login(response, req);
    	return Result.success(true);
    }
}
