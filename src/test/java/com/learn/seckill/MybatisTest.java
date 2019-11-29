package com.learn.seckill;

import com.learn.seckill.dto.request.TestReq;
import com.learn.seckill.dto.response.TestResp;
import com.learn.seckill.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: seckill:MybatisTest
 * @description:
 * @author: zcf
 * @create: 2019-11-29 10:44
 **/
@Slf4j
public class MybatisTest extends BaseTest {
    @Autowired
    private TestService testService;

    @Test
    public void insert() {
        TestReq testReq = new TestReq();
        int age = 30;
        testReq.setAge(age);
        testReq.setName("test" + age);
        Boolean insert = testService.insert(testReq);
        log.info("insert:{}",insert);
    }

    @Test
    public void testSelect() {
        TestReq testReq = new TestReq();
        testReq.setId(2);
        TestResp resp = testService.get(testReq);
        log.info("testSelect:{}",resp);
    }
}
