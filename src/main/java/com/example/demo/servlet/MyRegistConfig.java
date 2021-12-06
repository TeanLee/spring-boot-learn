package com.example.demo.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRegistConfig {
    @Bean
    public ServletRegistrationBean myServlet() {
        MyServlet myServlet = new MyServlet();

        return new ServletRegistrationBean(myServlet, "/my");
    }

    @Bean
    public FilterRegistrationBean myFilter() {

        MyFilter myFilter = new MyFilter();
        return new FilterRegistrationBean(myFilter, myServlet());  // myFilter 专门用来拦截 "/my" 路径的
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        MyServletContextListener myServletContextListener = new MyServletContextListener();
        return new ServletListenerRegistrationBean(myServletContextListener);
    }
}
