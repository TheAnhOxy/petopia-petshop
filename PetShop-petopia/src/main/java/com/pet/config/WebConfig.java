package com.pet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Cho phép truy cập ảnh qua URL /uploads/**
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/"); // nằm cùng cấp với project jar
    }
}
