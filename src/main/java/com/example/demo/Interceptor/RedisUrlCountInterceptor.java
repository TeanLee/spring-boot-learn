package com.example.demo.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 想要实现的功能：
 *      每次有请求，都判断某个路径的请求次数（为了演示 redis 的使用）
 */

@Component
public class RedisUrlCountInterceptor implements HandlerInterceptor {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    // ctrl + o 快捷键可以自动生成要 override 的方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();

        // increment 实际上对用的是 redis 中 key-value 的 value 自增
        // 默认每次访问当前 uri 就会计数 +1
        stringRedisTemplate.opsForValue().increment(uri);


        return true;
    }
}
