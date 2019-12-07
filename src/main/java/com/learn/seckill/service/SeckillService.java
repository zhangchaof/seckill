package com.learn.seckill.service;

import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.dto.OrderVO;
import com.learn.seckill.dto.SeckillUserVO;

/**
 * @program: seckill
 * @description:
 * @author: liu yan
 * @create: 2019-12-07 19:27
 */
public interface SeckillService {
    OrderVO seckill(SeckillUserVO userVO, GoodsVo goods);
}
