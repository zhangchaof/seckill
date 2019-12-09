package com.learn.seckill.service;

import com.learn.seckill.dto.GoodsVo;

import java.util.List;

/**
 * @program: seckill
 * @description:
 * @author: zcf
 * @create: 2019-12-06 15:40
 */
public interface GoodsService {
    /**
     * 获取商品列表
     *
     * @return
     */
    List<GoodsVo> listGoodsVo();

    /**
     * 通过商品编码查询商品详情
     *
     * @param goodsCode
     * @return
     */
    GoodsVo getGoodsVoByGoodsCode(String goodsCode);

}
