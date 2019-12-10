package com.learn.seckill.dao;

import com.learn.seckill.dto.SeckillOrderVO;
import com.learn.seckill.entity.SeckillOrderEntity;
import org.apache.ibatis.annotations.Param;

public interface SeckillOrderEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill_order
     *
     * @mbg.generated
     */
    int insert(SeckillOrderEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill_order
     *
     * @mbg.generated
     */
    int insertSelective(SeckillOrderEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill_order
     *
     * @mbg.generated
     */
    SeckillOrderEntity selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SeckillOrderEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SeckillOrderEntity record);

    /**
     * @param userId
     * @param goodsCode
     * @return
     */
    SeckillOrderEntity getSeckillOrderBySeckillUserIdGoodsCode(@Param("seckillUserId") Long seckillUserId, @Param("goodsCode") String goodsCode);
}