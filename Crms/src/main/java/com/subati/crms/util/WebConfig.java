package com.subati.crms.util;

//跨域处理
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//全局配置类--配置跨越请求
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * 1. 与访问路径
     * 2. 请求来源
     * 3. 方法
     * 4. 允许携带
     * 5. 最大时间（响应时间）
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("Http://localhost:8081","null")
                .allowedMethods("GET","POST","PUT","OPTIONS","DELETE")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
