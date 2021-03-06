package com.learn.seckill.service.impl;

import com.learn.seckill.dao.SeckillUserEntityMapper;
import com.learn.seckill.dto.request.LoginReq;
import com.learn.seckill.entity.SeckillUserEntity;
import com.learn.seckill.exception.GlobalException;
import com.learn.seckill.result.CodeMsg;
import com.learn.seckill.service.SeckillUserService;
import com.learn.seckill.utils.MD5Util;
import com.learn.seckill.redis.RedisUtil;
import com.learn.seckill.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.learn.seckill.redis.RedisConstant.*;

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

    public SeckillUserEntity selectByMobile(LoginReq req) {
        //取缓存
        Object seckillUserObj = redisService.get(req.getMobile());
        if (seckillUserObj != null) {
            SeckillUserEntity userEntity = (SeckillUserEntity) seckillUserObj;
            return userEntity;
        }
        //取数据库
        SeckillUserEntity user = userEntityMapper.selectByMobile(req);
        if (user != null) {
            redisService.set(req.getMobile(), user);
        }
        return user;
    }

    /**
     * 获取用户登录信息
     *
     * @param response
     * @param token
     * @return
     */

    @Override
    public SeckillUserEntity getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Object userValue = redisService.get(SECKILL_USER_PREFIX.concat(token));
        SeckillUserEntity user = null;
        //延长有效期
        if (userValue != null) {
            user = (SeckillUserEntity) userValue;
            addCookie(response, token, user);
        }
        return user;
    }

    /**
     * 用户登录
     *
     * @param response
     * @param req
     * @return
     */
    @Override
    public boolean login(HttpServletResponse response, LoginReq req) {
        if (req == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String formPass = req.getPassword();
        //判断手机号是否存在
        SeckillUserEntity user = selectByMobile(req);
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
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return true;
    }
    // http://blog.csdn.net/tTU1EvLDeLFq5btqiK/article/details/78693323
    //更新数据库,失效缓存，设置缓存
    public boolean updatePassword(String token, LoginReq req, String formPass) {
        //取user
        SeckillUserEntity user = selectByMobile(req);
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //更新数据库
        SeckillUserEntity toBeUpdate = new SeckillUserEntity();
        toBeUpdate.setMobile(req.getMobile());
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        userEntityMapper.updateByPrimaryKeySelective(toBeUpdate);
        //处理缓存
        redisService.del(req.getMobile());
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(SECKILL_USER_PREFIX.concat(token), user);
        return true;
    }

    private void addCookie(HttpServletResponse response, String token, SeckillUserEntity user) {
        redisService.set(SECKILL_USER_PREFIX.concat(token), user, TOKEN_EXPIRE);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
