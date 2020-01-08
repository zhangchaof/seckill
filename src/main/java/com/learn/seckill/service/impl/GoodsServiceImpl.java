package com.learn.seckill.service.impl;

import com.learn.seckill.dao.GoodsEntityMapper;
import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.entity.SeckillGoodsEntity;
import com.learn.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: seckill:GoodsServiceImpl
 * @description:
 * @author: zcf
 * @create: 2019-12-06 15:42
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsEntityMapper goodsEntityMapper;

    @Override
    public List<GoodsVo> listGoodsVo() {
        return goodsEntityMapper.listGoodsVo();
    }

    @Override
    public GoodsVo getGoodsVoByGoodsCode(String goodsCode) {
        return goodsEntityMapper.getGoodsVoByGoodsCode(goodsCode);
    }
}
