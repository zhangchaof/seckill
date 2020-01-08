package com.learn.seckill.service.impl;

import com.learn.seckill.dao.SeckillOrderEntityMapper;
import com.learn.seckill.dto.GoodsVo;
import com.learn.seckill.dto.OrderVO;
import com.learn.seckill.dto.SeckillOrderVO;
import com.learn.seckill.dto.SeckillUserVO;
import com.learn.seckill.entity.OrderEntity;
import com.learn.seckill.entity.SeckillOrderEntity;
import com.learn.seckill.redis.RedisConstant;
import com.learn.seckill.redis.RedisUtil;
import com.learn.seckill.service.OrderService;
import com.learn.seckill.service.SeckillGoodsService;
import com.learn.seckill.service.SeckillOrderService;
import com.learn.seckill.service.SeckillService;
import com.learn.seckill.utils.Constant;
import com.learn.seckill.utils.MD5Util;
import com.learn.seckill.utils.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.learn.seckill.redis.RedisConstant.SECKILL_USER_ORDER;


/**
 * @program: seckill:SeckillServiceImpl
 * @description:
 * @author: zcf
 * @create: 2019-12-07 19:35
 **/
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillOrderEntityMapper seckillOrderEntityMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Autowired
    private SeckillOrderService seckillOrderService;

    @Autowired
    RedisUtil redisService;

    @Override
    public SeckillOrderVO getSeckillOrderBySeckillUserIdGoodsCode(Long userId, String goodsCode) {
        SeckillOrderVO seckillOrderVO = null;
        String key = SECKILL_USER_ORDER + userId + goodsCode;
        SeckillOrderEntity seckillOrderEntity = (SeckillOrderEntity) redisService.get(key);
        if (!Objects.isNull(seckillOrderEntity)) {
            seckillOrderVO = new SeckillOrderVO();
            BeanUtils.copyProperties(seckillOrderEntity, seckillOrderVO);
        }
        return seckillOrderVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderVO seckill(SeckillUserVO userVO, GoodsVo goods) {
        //减库存
        Integer stock = seckillGoodsService.reduceStock(goods);
        //下订单
        if (stock > 0) {
            OrderEntity orderEntity = orderService.createOrder(userVO, goods);
            // 写入秒杀订单
            seckillOrderService.createOrder(orderEntity);
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(orderEntity, orderVO);
            return orderVO;
        } else {
            setGoodsOver(goods.getGoodsCode());
            return null;
        }

    }

    @Override
    public Long getSeckillResult(Long userId, String goodsCode) {

        SeckillOrderEntity order = seckillOrderEntityMapper.getSeckillOrderBySeckillUserIdGoodsCode(userId, goodsCode);
        //秒杀成功
        if (order != null) {
            return Long.valueOf(order.getOrderNo());
        } else {
            boolean isOver = getGoodsOver(goodsCode);
            if (isOver) {
                return Constant.SECKILL_FAILTURE;
            } else {
                return Constant.SECKILL_PEDDING;
            }
        }
    }

    private void setGoodsOver(String goodsCode) {
        redisService.set(RedisConstant.SECKILL_OVER.concat(goodsCode), true);
    }

    private boolean getGoodsOver(String goodsCode) {
        return redisService.hasKey(RedisConstant.SECKILL_OVER.concat(goodsCode));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reset(List<GoodsVo> goodsList) {
        seckillGoodsService.resetStock(goodsList);
        seckillOrderEntityMapper.deleteSeckillOrders();
        orderService.deleteOrders();
    }

    @Override
    public String createSeckillPath(SeckillUserVO user, String goodsCode) {
        if (Objects.isNull(user) || Objects.isNull(goodsCode)) {
            return null;
        }
        String str = MD5Util.md5(UUIDUtil.uuid() + "123456");
        redisService.set(RedisConstant.SKILL_PATH.concat(user.getUserId().toString()).concat(goodsCode), str, RedisConstant.SKILL_PATH_EXPIRE);
        return str;
    }

    @Override
    public boolean checkPath(SeckillUserVO user, String goodsCode, String path) {
        if (user == null || path == null) {
            return false;
        }

        String pathOld = redisService.get(RedisConstant.SKILL_PATH.concat(user.getUserId().toString()).concat(goodsCode)) + "";
        return path.equals(pathOld);
    }
}
