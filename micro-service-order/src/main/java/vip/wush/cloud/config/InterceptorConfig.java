package vip.wush.cloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @ClassName: IntercetporConfig
 * @Description: TODO
 * @Author: wush
 * @Date: 2020/4/23 23:30
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Resource
    private BaseInterceptor baseInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor).addPathPatterns("/**");
    }


}
