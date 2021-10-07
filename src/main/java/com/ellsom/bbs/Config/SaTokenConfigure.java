package com.ellsom.bbs.Config;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    /**
     * sa-token权限注解开关
     */
    @Value("${spring.sa-token.isOpen}")
    private boolean isOpen;

    @Primary
    @Bean(name = "SaTokenConfigure")
    public SaTokenConfig getSaTokenConfig() {
        SaTokenConfig config = new SaTokenConfig();
        config.setTokenName("EllsomToken");
        config.setTimeout(-1);
        config.setActivityTimeout(-1);
        config.setAllowConcurrentLogin(true);
        config.setIsShare(true);
        config.setTokenStyle("uuid");
        return config;

    }


    //注册sa-token的注解拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * sa-token开关
         */
        if (isOpen){
            registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
        }

    }

}
