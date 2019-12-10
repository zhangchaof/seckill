package com.learn.seckill.service;

import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.dto.OrderVO;
import com.learn.seckill.dto.SeckillOrderVO;
import com.learn.seckill.dto.SeckillUserVO;

/**
 * @program: seckill
 * @description:
 * @author: zcf
 * @create: 2019-12-07 19:27
 */
public interface SeckillService {

    SeckillOrderVO getSeckillOrderBySeckillUserIdGoodsCode(Long userId, String goodsCode);

    OrderVO seckill(SeckillUserVO userVO, GoodsVo goods);
}
