package com.learn.seckill.service.impl;

import com.learn.seckill.dao.SeckillOrderEntityMapper;
import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.dto.OrderVO;
import com.learn.seckill.dto.SeckillOrderVO;
import com.learn.seckill.dto.SeckillUserVO;
import com.learn.seckill.service.GoodsService;
import com.learn.seckill.service.OrderService;
import com.learn.seckill.service.SeckillGoodsService;
import com.learn.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private GoodsService goodsService;

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Override
    public SeckillOrderVO getSeckillOrderBySeckillUserIdGoodsCode(String userId, String goodsCode) {
        return seckillOrderEntityMapper.getSeckillOrderBySeckillUserIdGoodsCode(userId, goodsCode);
    }

    @Override
    public OrderVO seckill(SeckillUserVO userVO, GoodsVo goods) {
        //减库存
        seckillGoodsService.reduceStock(goods);
        //下订单

        // 写入秒杀订单
        return orderService.createOrder(user, goods);
    }
}
