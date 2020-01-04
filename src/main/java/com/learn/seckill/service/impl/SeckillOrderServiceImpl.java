package com.learn.seckill.service.impl;

import com.learn.seckill.dao.SeckillOrderEntityMapper;
import com.learn.seckill.entity.OrderEntity;
import com.learn.seckill.entity.SeckillOrderEntity;
import com.learn.seckill.redis.RedisUtil;
import com.learn.seckill.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.learn.seckill.redis.RedisConstant.SECKILL_USER_ORDER;

/**
 * @program: seckill
 * @description:
 * @author: zcf
 * @create: 2019-12-10 00:15:40
 */
@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {
    @Autowired
    private SeckillOrderEntityMapper seckillOrderEntityMapper;

    @Autowired
    RedisUtil redisService;

    @Override
    public int createOrder(OrderEntity orderEntity) {
        SeckillOrderEntity seckillOrderEntity = new SeckillOrderEntity();
        seckillOrderEntity.setGoodsCode(orderEntity.getGoodsCode());
        seckillOrderEntity.setOrderNo(orderEntity.getOrderNo());
        seckillOrderEntity.setSeckillUserId(orderEntity.getSeckillUserId());

        redisService.set(SECKILL_USER_ORDER.concat(orderEntity.getSeckillUserId().toString()).concat(orderEntity.getGoodsCode()),orderEntity);
        return seckillOrderEntityMapper.insert(seckillOrderEntity);
    }
}
