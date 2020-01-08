package com.learn.seckill.service;

import com.learn.seckill.dto.GoodsVo;

import java.util.List;

/**
 * @program: seckill
 * @description:
 * @author: zcf
 * @create: 2019-12-09 18:03
 */
public interface SeckillGoodsService {

    /**
     * 扣减库存
     */
    Integer reduceStock(GoodsVo goodsVo);

    /**
     * 还原库存
     * @param goodsList
     */
    void resetStock(List<GoodsVo> goodsList);
}
