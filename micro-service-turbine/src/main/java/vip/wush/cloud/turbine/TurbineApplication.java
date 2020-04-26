package vip.wush.cloud.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * @ClassName: TurbineApplication
 * @Description: Turbine app
 * @Author: wush
 * @Date: 2020/4/24 23:09
 */
@SpringBootApplication
//@EnableTurbine
@EnableTurbineStream
@EnableDiscoveryClient
public class TurbineApplication {

    public static void main(String[] args) {

        SpringApplication.run(TurbineApplication.class, args);
    }



}
