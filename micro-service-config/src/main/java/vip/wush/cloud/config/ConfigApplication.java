package vip.wush.cloud.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @ClassName: ConfigApplication
 * @Description: TODO
 * @Author: wush
 * @Date: 2020/5/2 16:32
 */

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ConfigApplication.class)
                .web(true)
                .run(args);

    }


}
