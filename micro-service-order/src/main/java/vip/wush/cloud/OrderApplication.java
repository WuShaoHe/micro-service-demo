package vip.wush.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @ClassName: OrderApplication
 * @Description: order server
 * @Author: wush
 * @Date: 2020/4/23 16:26
 */

@SpringBootApplication
@EnableDiscoveryClient  //eureka 注解
@EnableHystrix          //Hystrix 注解
public class OrderApplication {

    public static void main(String[] args) {

        SpringApplication.run(OrderApplication.class, args);

    }

}
