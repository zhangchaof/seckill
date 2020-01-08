package com.learn.seckill.config;

import com.learn.seckill.access.UserContext;
import com.learn.seckill.dto.SeckillUserVO;
import com.learn.seckill.entity.SeckillUserEntity;
import com.learn.seckill.service.SeckillUserService;
import com.learn.seckill.redis.RedisConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @program: seckill
 * @description:
 * @author: zcf
 * @create: 2019-12-03 23:11:19
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    SeckillUserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return clazz == SeckillUserVO.class;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return UserContext.getUser();
    }
}
