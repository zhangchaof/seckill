package com.learn.seckill.config;

import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.redis.RedisConstant;
import com.learn.seckill.redis.RedisUtil;
import com.learn.seckill.service.GoodsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: seckill
 * @description:
 * @author: zcf
 * @create: 2020-01-05 14:07:13
 */
@Component
public class Init implements InitializingBean {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisUtil redisService;

    public static Map<String, Boolean> localOverMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        if (goodsList == null) {
            return;
        }
        for (GoodsVo goods : goodsList) {
            redisService.set(RedisConstant.SECKILL_GOODS_STOCK.concat(goods.getGoodsCode()), goods.getStockCount());
            localOverMap.put(goods.getGoodsCode(), false);
        }
    }
}
