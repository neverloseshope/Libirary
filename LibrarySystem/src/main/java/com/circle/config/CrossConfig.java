package com.circle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CrossConfig {
    //跨域源白名单
    private String[] origins={
            "localhost",
            "127.0.0.1",
            "127.0.0.1:8081",
            "localhost:8081"
    };

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //添加允许跨域的来源ip或域名
        for(String origin:origins){
            corsConfiguration.addAllowedOrigin("http://"+origin);
            corsConfiguration.addAllowedOrigin("https://"+origin);
        }
        //支持所有http method
        corsConfiguration.addAllowedMethod("*");
        //对请求头属性无限制
        corsConfiguration.addAllowedHeader("*");
        //允许发送cookie
        corsConfiguration.setAllowCredentials(true);
        //基于Url路径配置跨域策略
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(source);
    }
}
