package com.learn.seckill.service;

import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.dto.OrderVO;
import com.learn.seckill.dto.SeckillUserVO;
import com.learn.seckill.entity.OrderEntity;

/**
 * @program: seckill
 * @description:
 * @author: zcf
 * @create: 2019-12-07 17:00
 */
public interface OrderService {
    /**
     * 创建订单
     * @param user
     * @param goods
     * @return
     */
     OrderEntity createOrder(SeckillUserVO user, GoodsVo goods);

    /**
     * 根据订单号查询订单
     * @param orderNo
     * @return
     */
    OrderVO getOrderByNo(String orderNo);

    Integer deleteOrders();
}
