package vip.wush.cloud.elk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: ELKApplication
 * @Description: elk app
 * @Author: wush
 * @Date: 2020/4/26 13:24
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ELKApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELKApplication.class, args);
    }

}
