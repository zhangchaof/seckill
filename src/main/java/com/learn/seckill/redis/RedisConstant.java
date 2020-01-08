package com.learn.seckill.redis;

/**
 * @program: seckill
 * @description:
 * @author: zcf
 * @create: 2019-12-03 22:44:21
 */
public class RedisConstant {

    public static final String COOKI_NAME_TOKEN = "token";
    public static final String SECKILL_USER_PREFIX = "tk";
    public static final int TOKEN_EXPIRE = 3600;

    public static final int GOODS_EXPIRE = 60;
    public static final String GOODS_LIST ="goodsList";
    public static final String  GOODS_DETAILS = "goodsDetails";
    public static final String SECKILL_USER_ORDER = "seckill_user_order";

    public static final String SECKILL_GOODS_STOCK = "gs";
    public static final String SECKILL_OVER = "seckll_over";

    public static final String SKILL_PATH = "sp";
    public static final int SKILL_PATH_EXPIRE = 60;

    public static final String SECKILL_VERIFY_CODE = "svc";
    public static final int SECKILL_VERIFY_CODE_EXPIRE = 300;

}
