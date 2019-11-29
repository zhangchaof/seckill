package com.learn.seckill.service;

import com.learn.seckill.dto.request.TestReq;
import com.learn.seckill.dto.response.TestResp;

/**
 * @author chaofan.zhang
 */
public interface TestService {
    /**
     * 获取信息
     * @param req
     * @return
     */
    TestResp get(TestReq req);

    /**
     * 插入数据
     * @param req
     * @return
     */
    Boolean insert(TestReq req);

    /**
     * 测试事物
     * @param req
     * @return
     */
    Boolean transaction(TestReq req);
}
