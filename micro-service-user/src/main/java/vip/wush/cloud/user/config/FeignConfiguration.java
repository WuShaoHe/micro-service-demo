package vip.wush.cloud.user.config;

import feign.Contract;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: FeignConfiguration
 * @Description: Feign config
 * @Author: wush
 * @Date: 2020/4/23 20:11
 */
@Configuration
public class FeignConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    //Http Basic认证
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "password1 ");
    }


}
