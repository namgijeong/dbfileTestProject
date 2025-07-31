package com.example.test3.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.test3.interceptor.LoginCheckInterceptor;

public class WebMVCConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                //.addPathPatterns("/user/**")
                .excludePathPatterns("/static/**");
    }
}
