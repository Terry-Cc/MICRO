package com.micro.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  跨域配置, 如果有网关模块则使用网关配置跨域, 没有则使用单独的配置
 * </p>
 * @since 2023/6/13 17:41
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter () {
        // 跨域配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //设置跨域的配置信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许的来源
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        // 若springboot版本为2.4.0以后，则设置请求源为
        // corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        // 允许的请求头
        corsConfiguration.addAllowedHeader("Content-Type, x-requested-with, X-Custom-Header");
        // 允许的方法
        corsConfiguration.addAllowedMethod("POST");
        // 允许凭证
        corsConfiguration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
