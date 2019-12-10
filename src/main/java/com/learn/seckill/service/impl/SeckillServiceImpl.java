package com.learn.seckill.service.impl;

import com.learn.seckill.dao.SeckillOrderEntityMapper;
import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.dto.OrderVO;
import com.learn.seckill.dto.SeckillOrderVO;
import com.learn.seckill.dto.SeckillUserVO;
import com.learn.seckill.entity.OrderEntity;
import com.learn.seckill.entity.SeckillOrderEntity;
import com.learn.seckill.service.OrderService;
import com.learn.seckill.service.SeckillGoodsService;
import com.learn.seckill.service.SeckillOrderService;
import com.learn.seckill.service.SeckillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @program: seckill:SeckillServiceImpl
 * @description:
 * @author: zcf
 * @create: 2019-12-07 19:35
 **/
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillOrderEntityMapper seckillOrderEntityMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Autowired
    private SeckillOrderService seckillOrderService;

    @Override
    public SeckillOrderVO getSeckillOrderBySeckillUserIdGoodsCode(String userId, String goodsCode) {
        SeckillOrderVO seckillOrderVO = null;
        SeckillOrderEntity seckillOrderEntity = seckillOrderEntityMapper.getSeckillOrderBySeckillUserIdGoodsCode(userId, goodsCode);
        if (!Objects.isNull(seckillOrderEntity)) {
            seckillOrderVO = new SeckillOrderVO();
            BeanUtils.copyProperties(seckillOrderEntity, seckillOrderVO);
        }
        return seckillOrderVO;
    }

    @Override
    @Transactional
    public OrderVO seckill(SeckillUserVO userVO, GoodsVo goods) {
        //减库存
        seckillGoodsService.reduceStock(goods);
        //下订单
        OrderEntity orderEntity = orderService.createOrder(userVO, goods);
        // 写入秒杀订单
        seckillOrderService.createOrder(orderEntity);
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderEntity, orderVO);
        return orderVO;
    }
}
