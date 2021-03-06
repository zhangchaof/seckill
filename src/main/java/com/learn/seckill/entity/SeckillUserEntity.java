package com.learn.seckill.entity;

import java.util.Date;

public class SeckillUserEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_user.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_user.user_id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_user.mobile
     *
     * @mbg.generated
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_user.nickname
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_user.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_user.salt
     *
     * @mbg.generated
     */
    private String salt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_user.head
     *
     * @mbg.generated
     */
    private String head;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_user.register_date
     *
     * @mbg.generated
     */
    private Date registerDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_user.last_login_date
     *
     * @mbg.generated
     */
    private Date lastLoginDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column seckill_user.login_count
     *
     * @mbg.generated
     */
    private Integer loginCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_user.id
     *
     * @return the value of seckill_user.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_user.id
     *
     * @param id the value for seckill_user.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_user.user_id
     *
     * @return the value of seckill_user.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_user.user_id
     *
     * @param userId the value for seckill_user.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId ;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_user.mobile
     *
     * @return the value of seckill_user.mobile
     *
     * @mbg.generated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_user.mobile
     *
     * @param mobile the value for seckill_user.mobile
     *
     * @mbg.generated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_user.nickname
     *
     * @return the value of seckill_user.nickname
     *
     * @mbg.generated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_user.nickname
     *
     * @param nickname the value for seckill_user.nickname
     *
     * @mbg.generated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_user.password
     *
     * @return the value of seckill_user.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_user.password
     *
     * @param password the value for seckill_user.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_user.salt
     *
     * @return the value of seckill_user.salt
     *
     * @mbg.generated
     */
    public String getSalt() {
        return salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_user.salt
     *
     * @param salt the value for seckill_user.salt
     *
     * @mbg.generated
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_user.head
     *
     * @return the value of seckill_user.head
     *
     * @mbg.generated
     */
    public String getHead() {
        return head;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_user.head
     *
     * @param head the value for seckill_user.head
     *
     * @mbg.generated
     */
    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_user.register_date
     *
     * @return the value of seckill_user.register_date
     *
     * @mbg.generated
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_user.register_date
     *
     * @param registerDate the value for seckill_user.register_date
     *
     * @mbg.generated
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_user.last_login_date
     *
     * @return the value of seckill_user.last_login_date
     *
     * @mbg.generated
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_user.last_login_date
     *
     * @param lastLoginDate the value for seckill_user.last_login_date
     *
     * @mbg.generated
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column seckill_user.login_count
     *
     * @return the value of seckill_user.login_count
     *
     * @mbg.generated
     */
    public Integer getLoginCount() {
        return loginCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column seckill_user.login_count
     *
     * @param loginCount the value for seckill_user.login_count
     *
     * @mbg.generated
     */
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
}