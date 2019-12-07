package com.learn.seckill.service;

import com.learn.seckill.dto.SeckillOrderVO;

/**
 * @program: seckill
 * @description:
 * @author: liu yan
 * @create: 2019-12-07 17:00
 */
public interface OrderService {
    SeckillOrderVO getSeckillOrderBySeckillUserIdGoodsCode(String userId, String goodsCode);
}
