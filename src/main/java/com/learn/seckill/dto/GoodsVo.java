package com.learn.seckill.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: seckill:GoodsVo
 * @description:
 * @author: zcf
 * @create: 2019-12-06 15:39
 **/
@Data
public class GoodsVo {
    private String goodsCode;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;

    private BigDecimal seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
