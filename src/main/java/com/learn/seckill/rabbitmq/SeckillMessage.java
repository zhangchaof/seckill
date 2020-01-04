package com.learn.seckill.rabbitmq;

import com.learn.seckill.dto.SeckillUserVO;
import lombok.Data;

@Data
public class SeckillMessage {
    private SeckillUserVO user;
    private String goodsCode;
}
