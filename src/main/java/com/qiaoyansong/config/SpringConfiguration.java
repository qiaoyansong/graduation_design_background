package com.qiaoyansong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/1 22:08
 * description：
 */
@Configuration
public class SpringConfiguration {
    /**
     * 配置静态资源映射
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**").addResourceLocations("file:F:/graduate_design/images/");
                registry.addResourceHandler("/login/**").addResourceLocations("file:F:/graduate_design/images/login/");
            }
        };
    }
}
