package com.learn.seckill.access;

import com.learn.seckill.dto.SeckillUserVO;

public class UserContext {

    private static ThreadLocal<SeckillUserVO> userHolder = new ThreadLocal<SeckillUserVO>();

    public static void setUser(SeckillUserVO user) {
        userHolder.set(user);
    }

    public static SeckillUserVO getUser() {
        return userHolder.get();
    }

}
