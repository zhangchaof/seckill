package com.learn.seckill.service.impl;

import com.learn.seckill.dao.TestEntityMapper;
import com.learn.seckill.dto.request.TestReq;
import com.learn.seckill.dto.response.TestResp;
import com.learn.seckill.entity.TestEntity;
import com.learn.seckill.service.TestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author chaofan.zhang
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestEntityMapper entityMapper;

    @Override
    public TestResp get(TestReq req) {
        TestEntity testEntity = entityMapper.selectByPrimaryKey(req.getId());
        TestResp resp = new TestResp();
        if (!Objects.isNull(testEntity)) {
            BeanUtils.copyProperties(testEntity, resp);
        }
        return resp;
    }

    @Override
    public Boolean insert(TestReq req) {
        TestEntity testEntity = new TestEntity();
        BeanUtils.copyProperties(req, testEntity);
        int count = entityMapper.insertSelective(testEntity);
        return count > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
    @Override
    @Transactional
    public Boolean transaction(TestReq req) {
        TestEntity testEntity = new TestEntity();
        BeanUtils.copyProperties(req, testEntity);
        int count = entityMapper.insertSelective(testEntity);
        int i = 1 / 0;
        TestEntity testEntity2 = new TestEntity();
        int count2 = entityMapper.insertSelective(testEntity2);

        return null;
    }
}
