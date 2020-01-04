package com.learn.seckill.controller;

import com.learn.seckill.rabbitmq.MQSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

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

    @Autowired
    MQSender mqSender;

    @GetMapping(value = "/sendQueue")
    @ResponseBody
    public void sendQueue(@NotNull @RequestParam("message") String message) {
        mqSender.sendQueue(message);
    }

    @GetMapping(value = "/sendTopic")
    @ResponseBody
    public void sendTopic(@NotNull @RequestParam("message") String message) {
        mqSender.sendTopic(message);
    }

    @GetMapping(value = "/sendFanout")
    @ResponseBody
    public void sendFanout(@NotNull @RequestParam("message") String message) {
        mqSender.sendFanout(message);
    }

    @GetMapping(value = "/sendHeader")
    @ResponseBody
    public void sendHeader(@NotNull @RequestParam("message") String message) {
        mqSender.sendHeader(message);
    }

    @RequestMapping(value = "/thymeleaf", method = RequestMethod.GET)
    public String thymeleaf(Model model) {
        model.addAttribute("test", "test");
        return "test";
    }
}
