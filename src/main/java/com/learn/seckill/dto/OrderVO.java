package com.learn.seckill.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: seckill:OrderVO
 * @description:
 * @author: zcf
 * @create: 2019-12-07 19:40
 **/
@Data
public class OrderVO {
    private String orderNo;

    private Long seckillUserId;

    private String goodsCode;

    private Long deliveryAddrId;

    private String goodsName;

    private Integer goodsCount;

    private BigDecimal goodsPrice;

    private Byte orderChannel;

    private Byte status;

    private Date createDate;

    private Date payDate;
}
