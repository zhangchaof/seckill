package com.learn.seckill.entity;

public class SeckillOrderEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_order.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_order.seckill_user_id
     *
     * @mbg.generated
     */
    private Long seckillUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_order.order_no
     *
     * @mbg.generated
     */
    private String orderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_order.goods_code
     *
     * @mbg.generated
     */
    private String goodsCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_order.id
     *
     * @return the value of seckill_order.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_order.id
     *
     * @param id the value for seckill_order.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_order.seckill_user_id
     *
     * @return the value of seckill_order.seckill_user_id
     *
     * @mbg.generated
     */
    public Long getSeckillUserId() {
        return seckillUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_order.seckill_user_id
     *
     * @param seckillUserId the value for seckill_order.seckill_user_id
     *
     * @mbg.generated
     */
    public void setSeckillUserId(Long seckillUserId) {
        this.seckillUserId = seckillUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_order.order_no
     *
     * @return the value of seckill_order.order_no
     *
     * @mbg.generated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_order.order_no
     *
     * @param orderNo the value for seckill_order.order_no
     *
     * @mbg.generated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_order.goods_code
     *
     * @return the value of seckill_order.goods_code
     *
     * @mbg.generated
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_order.goods_code
     *
     * @param goodsCode the value for seckill_order.goods_code
     *
     * @mbg.generated
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }
}