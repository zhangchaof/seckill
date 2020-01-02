package com.learn.seckill.dto;

import lombok.Data;

/**
 * @program: seckill:OrderDetailVo
 * @description:
 * @author: zcf
 * @create: 2020-01-02 10:30
 **/
@Data
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderVO order;
}
