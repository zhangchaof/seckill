package com.learn.seckill.service;

import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.dto.SeckillUserVO;
import com.learn.seckill.entity.OrderEntity;

/**
 * @program: seckill
 * @description:
 * @author: zcf
 * @create: 2019-12-07 17:00
 */
public interface OrderService {
    OrderEntity createOrder(SeckillUserVO user, GoodsVo goods);
}
