package com.qiaoyansong.config;

import com.qiaoyansong.interceptor.CheckIsLogIn;
import com.qiaoyansong.interceptor.CheckIsLogInRemotely;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/1 22:08
 * description：
 */
@Configuration
public class SpringConfiguration {
    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }
    /**
     * 配置静态资源映射
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/image/**").addResourceLocations("file:F:/graduate_design/images/");
                registry.addResourceHandler("/image/register/**").addResourceLocations("file:F:/graduate_design/images/register/");
                registry.addResourceHandler("/image/login/**").addResourceLocations("file:F:/graduate_design/images/login/");
                registry.addResourceHandler("/upload/pic/**").addResourceLocations("file:F:/graduate_design/images/upload/pic/");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new CheckIsLogInRemotely()).addPathPatterns("/**").excludePathPatterns("/login/**").excludePathPatterns("/register/**").excludePathPatterns("/favicon.ico").excludePathPatterns("/upload/**").excludePathPatterns("/image/**").excludePathPatterns("/modifypwd/**").excludePathPatterns("/home/**").excludePathPatterns("/news/**").excludePathPatterns("/activity/**").excludePathPatterns("/commodity/**").excludePathPatterns("/auction/**").excludePathPatterns("seekHelpList").excludePathPatterns("/order/**");
                registry.addInterceptor(new CheckIsLogIn()).addPathPatterns("/**").excludePathPatterns("/login/**").excludePathPatterns("/register/**").excludePathPatterns("/favicon.ico").excludePathPatterns("/upload/**").excludePathPatterns("/getSaveInfo").excludePathPatterns("/image/**").excludePathPatterns("/modifypwd/**").excludePathPatterns("/home/**").excludePathPatterns("/news/**").excludePathPatterns("/activity/**").excludePathPatterns("/commodity/**").excludePathPatterns("/auction/**").excludePathPatterns("seekHelpList").excludePathPatterns("/order/**");
            }

//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        // 设置允许跨域请求的域名
//                        .allowedOriginPatterns("*")
//                        // 是否允许证书（cookies）
//                        .allowCredentials(true)
//                        // 设置允许的方法
//                        .allowedMethods("*")
//                        // 跨域允许时间
//                        .maxAge(3600);
//            }
        };
    }
}
