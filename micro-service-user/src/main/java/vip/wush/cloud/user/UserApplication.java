package vip.wush.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import vip.wush.cloud.user.config.FeignConfiguration;

/**
 * @ClassName: UserApplication
 * @Description: user server
 * @Author: wush
 * @Date: 2020/4/23 15:00
 */

@SpringBootApplication
//不扫描FeignConfiguration类， 防止配置信息被所有@FeignClient共享
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {FeignConfiguration.class})})
@EnableDiscoveryClient  //eureka 注解
@EnableFeignClients     //feign注解
@EnableHystrix          //Hystrix 注解
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
