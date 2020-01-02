package com.learn.seckill.dto;

import lombok.Data;

/**
 * @program: seckill:GoodsDetailVo
 * @description:
 * @author: zcf
 * @create: 2020-01-02 14:13
 **/
@Data
public class GoodsDetailVo {
    private int seckillStatus = 0;
    private int remainSeconds = 0;
    private GoodsVo goods ;
    private SeckillUserVO user;
}
