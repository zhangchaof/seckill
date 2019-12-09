package com.learn.seckill.service;

import com.learn.seckill.entity.OrderEntity;

/**
 * @program: seckill
 * @description:
 * @author: zcf
 * @create: 2019-12-10 00:15:21
 */
public interface SeckillOrderService {
    int createOrder(OrderEntity orderEntity);
}
