package com.learn.seckill.service.impl;

import com.learn.seckill.dao.SeckillGoodsEntityMapper;
import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.entity.SeckillGoodsEntity;
import com.learn.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: seckill:SeckillGoodsServiceImpl
 * @description:
 * @author: zcf
 * @create: 2019-12-09 18:04
 **/
@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    @Autowired
    private SeckillGoodsEntityMapper seckillGoodsEntityMapper;

    @Override
    public Integer reduceStock(GoodsVo goodsVo) {
        SeckillGoodsEntity seckillGoodsEntity = new SeckillGoodsEntity();
        seckillGoodsEntity.setGoodsCode(goodsVo.getGoodsCode());
        return seckillGoodsEntityMapper.reduceStock(seckillGoodsEntity);
    }

    @Override
    public void resetStock(List<GoodsVo> goodsList) {
        for(GoodsVo goods : goodsList ) {
            SeckillGoodsEntity g = new SeckillGoodsEntity();
            g.setGoodsCode(goods.getGoodsCode());
            g.setStockCount(goods.getStockCount());
            seckillGoodsEntityMapper.resetStock(g);
        }
    }
}
