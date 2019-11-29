package com.learn.seckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program: seckill:TestController
 * @description:
 * @author: zcf
 * @create: 2019-11-29 14:10
 **/
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping(value = "/thymeleaf", method = RequestMethod.GET)
    public String thymeleaf(Model model) {
        model.addAttribute("test", "test");
        return "test";
    }
}
