package com.learn.seckill.dao;

import com.learn.seckill.entity.SeckillUserEntity;

public interface SeckillUserEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill_user
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill_user
     *
     * @mbg.generated
     */
    int insert(SeckillUserEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill_user
     *
     * @mbg.generated
     */
    int insertSelective(SeckillUserEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill_user
     *
     * @mbg.generated
     */
    SeckillUserEntity selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SeckillUserEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SeckillUserEntity record);
}