package com.learn.seckill.service.impl;

import com.learn.seckill.dao.OrderEntityMapper;
import com.learn.seckill.dto.SeckillOrderVO;
import com.learn.seckill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: seckill:OrderServiceImpl
 * @description:
 * @author: zcf
 * @create: 2019-12-07 19:34
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderEntityMapper orderEntityMapper;


}
