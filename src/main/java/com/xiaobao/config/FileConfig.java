package com.xiaobao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置上传后的文件访问，使其不用重启也能访问到
 * @author 小暴
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {
    @Value("${web.upload-path}")
    public String path;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+path);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
