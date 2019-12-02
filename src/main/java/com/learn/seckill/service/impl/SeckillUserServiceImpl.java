package com.learn.seckill.service.impl;

import com.learn.seckill.dao.SeckillUserEntityMapper;
import com.learn.seckill.dto.request.LoginReq;
import com.learn.seckill.entity.SeckillUserEntity;
import com.learn.seckill.exception.GlobalException;
import com.learn.seckill.result.CodeMsg;
import com.learn.seckill.service.SeckillUserService;
import com.learn.seckill.utils.MD5Util;
import com.learn.seckill.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: seckill
 * @description: 秒杀用户
 * @author: zcf
 * @create: 2019-12-02 21:53:00
 */
@Service
public class SeckillUserServiceImpl implements SeckillUserService {

    @Autowired
    SeckillUserEntityMapper userEntityMapper;

    @Autowired
    RedisUtil redisService;

    public boolean login(HttpServletResponse response, LoginReq req) {
        if (req == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = req.getMobile();
        String formPass = req.getPassword();
        //判断手机号是否存在
        SeckillUserEntity user = userEntityMapper.selectByPrimaryKey(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        return true;
    }

}
