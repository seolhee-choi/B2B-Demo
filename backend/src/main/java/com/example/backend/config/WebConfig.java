package com.example.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public Intercepter intercepter() {
        return new Intercepter();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(intercepter())
                .excludePathPatterns("/api/v1/**")
        ;
    }
}
