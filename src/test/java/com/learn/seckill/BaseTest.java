package com.learn.seckill;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zcf
 * @Title: 集成测试只是针对controller类里面的方法
 * @ProjectName microservice-parent
 * @Description:集成测试api基类 1.集成测试类继承BaseTest可以实现统一加载测试环境配置文件
 * 2.所有的集成测试可以通过这个类统一管理
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SeckillApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseTest {
}
