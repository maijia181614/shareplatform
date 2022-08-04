package com.shareplatform.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//    配置图片路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的图片放在项目的img目录下
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/img/");
    }

}
