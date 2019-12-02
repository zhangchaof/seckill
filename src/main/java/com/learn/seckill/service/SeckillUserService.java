package com.learn.seckill.service;

import com.learn.seckill.dto.request.LoginReq;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: seckill
 * @description:
 * @author: zcf
 * @create: 2019-12-02 21:52:40
 */
public interface SeckillUserService {
    boolean login(HttpServletResponse response, LoginReq req);
}
