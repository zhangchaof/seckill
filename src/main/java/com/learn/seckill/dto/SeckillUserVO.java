package com.learn.seckill.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @program: seckill:SeckillUserVO
 * @description:
 * @author: zcf
 * @create: 2019-12-06 14:16
 **/
@Data
@ApiModel(value = "SeckillUserVO", description = "秒杀用户展示")
public class SeckillUserVO {
    private Long userId;
    private String mobile;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
