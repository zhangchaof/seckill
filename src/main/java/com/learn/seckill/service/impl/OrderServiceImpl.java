package com.learn.seckill.service.impl;

import com.learn.seckill.dao.OrderEntityMapper;
import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.dto.OrderVO;
import com.learn.seckill.dto.SeckillUserVO;
import com.learn.seckill.entity.OrderEntity;
import com.learn.seckill.service.OrderService;
import com.learn.seckill.utils.IdGen;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: seckill:OrderServiceImpl
 * @description:
 * @author: zcf
 * @create: 2019-12-07 19:34
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderEntityMapper orderEntityMapper;


    @Override
    public OrderEntity createOrder(SeckillUserVO user, GoodsVo goods) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(goods, orderEntity);
        orderEntity.setCreateDate(new Date());
        orderEntity.setDeliveryAddrId(0L);
        orderEntity.setGoodsCount(1);
        orderEntity.setGoodsCode(goods.getGoodsCode());
        orderEntity.setGoodsName(goods.getGoodsName());
        orderEntity.setGoodsPrice(goods.getSeckillPrice());
        orderEntity.setOrderChannel((byte) 1);
        orderEntity.setStatus((byte) 0);
        orderEntity.setOrderNo(IdGen.get().nextId() + "");
        orderEntity.setSeckillUserId(user.getUserId());
        orderEntityMapper.insert(orderEntity);
        return orderEntity;
    }

    @Override
    public OrderVO getOrderByNo(String orderNo) {
        OrderVO orderVO = new OrderVO();
        OrderEntity order = orderEntityMapper.getOrderByNo(orderNo);
        BeanUtils.copyProperties(order, orderVO);
        return orderVO;
    }
}
