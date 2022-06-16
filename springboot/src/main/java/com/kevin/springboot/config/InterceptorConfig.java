package com.kevin.springboot.config;

import com.kevin.springboot.config.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**配置拦截器 结合Token
 * @author: KevinWu
 * 2022/6/15 6:21 PM
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                // 拦截所有请求，通过判断token是否合法来决定是否需要登录
                .addPathPatterns("/**")
                // 放行以下路由传来的请求
                .excludePathPatterns("/user/login","/user/register","/**/export","/**/import","/file/**");
    }

    @Bean
    public JwtInterceptor jwtInterceptor(){
       return new JwtInterceptor();
    }
}
