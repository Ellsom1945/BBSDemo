package com.ellsom.bbs.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler
                ("/upload-file/upload/**")
                .addResourceLocations
                        ("file:H:/upload-file/upload/");
//        registry.addResourceHandler
//                ("/personal/static/**").
//                addResourceLocations("classpath:/static/static/");

    }
}
