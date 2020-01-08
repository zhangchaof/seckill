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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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
        if (Objects.isNull(user) || Objects.isNull(path)) {
            return false;
        }

        String pathOld = redisService.get(RedisConstant.SKILL_PATH.concat(user.getUserId().toString()).concat(goodsCode)) + "";
        return path.equals(pathOld);
    }

    @Override
    public BufferedImage createVerifyCode(SeckillUserVO user, String goodsCode) {
        if (Objects.isNull(user) || Objects.isNull(goodsCode)) {
            return null;
        }
        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String verifyCode = generateVerifyCode(rdm);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(verifyCode, 8, 24);
        g.dispose();
        //把验证码存到redis中
        int rnd = calc(verifyCode);
        redisService.set(RedisConstant.SECKILL_VERIFY_CODE.concat(user.getUserId().toString()).concat(goodsCode), rnd);
        //输出图片
        return image;
    }

    public boolean checkVerifyCode(SeckillUserVO user, String goodsCode, int verifyCode) {
        if (Objects.isNull(user) || Objects.isNull(goodsCode)) {
            return false;
        }
        String vcKey = RedisConstant.SECKILL_VERIFY_CODE.concat(user.getUserId().toString()).concat(goodsCode);
        Object codeObj = redisService.get(vcKey);
        if (Objects.isNull(codeObj) || Integer.valueOf(codeObj.toString()) - verifyCode != 0) {
            return false;
        }
        redisService.del(vcKey);
        return true;
    }

    private static int calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            return (Integer) engine.eval(exp);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static char[] ops = new char[]{'+', '-', '*'};

    /**
     * + - *
     */
    private String generateVerifyCode(Random rdm) {
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = ops[rdm.nextInt(3)];
        char op2 = ops[rdm.nextInt(3)];
        String exp = "" + num1 + op1 + num2 + op2 + num3;
        return exp;
    }
}
