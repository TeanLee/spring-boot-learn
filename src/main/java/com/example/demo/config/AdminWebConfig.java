package com.example.demo.config;

import com.example.demo.Interceptor.LoginInterceptor;
import com.example.demo.Interceptor.RedisUrlCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 1、编写一个拦截器实现 HandlerInterceptor 接口
 * 2、拦截器注册到容器中（实现 WebMvcConfigurer 的 addInterceptors）
 * 3、指定拦截规则【如果是拦截所有，静态资源也会被拦截】
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    /**
     * Filter、Interceptor 几乎拥有相同的功能，区别和各自的好处
     * 1、Filter是Servlet定义的原生组件，它的好处是脱离Spring应用也能使用。
     * 2、Interceptor是Spring定义的接口，可以使用Spring的自动装配等功能。（比如本程序中的 RedisUrlCountInterceptor 就是通过 @Component 和 @Autowired 实现自动装配的
     */

    // 由于 RedisUrlCountInterceptor 已经通过 @Component 声明注入到容器中了，因此可以通过自动注入获取 redisUrlCountInterceptor
    @Autowired
    RedisUrlCountInterceptor redisUrlCountInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**") // 所有请求都被拦截包括静态资源
//                .excludePathPatterns("/", "/login", "/css/**", "/js/**", "/fonts/**", "/images/**", "/upload"); // 放行的请求，把静态资源内容也放行


        // 拦截除所有静态资源的路径，统计访问每个路径的出现次数
        registry.addInterceptor(redisUrlCountInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**",
                        "/js/**","/aa/**");

        // 可以在 redis 库中通过【 keys * 】查看所有接收到的请求，并通过【 get key 】获取每个请求的访问次数。之后试着在访问 /main 路径的时候返回 redis 中拿到的次数
    }
}
