package com.learn.seckill;

import com.learn.seckill.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: seckill:RedisTest
 * @description:
 * @author: zcf
 * @create: 2019-11-29 13:58
 **/
@Slf4j
public class RedisTest extends BaseTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testSet() {
        boolean flag = redisUtil.set("test", "test");
        log.info("flag:{}", flag);
    }
    @Test
    public void testGet() {
        String value = redisUtil.get("test").toString();
        log.info("value:{}", value);
    }
}
