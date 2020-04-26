package xip.wush.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import xip.wush.cloud.zuul.config.MyFilter;

/**
 * @ClassName: ZuulApplication
 * @Description: zuul app
 * @Author: wush
 * @Date: 2020/4/25 12:00
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public MyFilter filter(){
        return new MyFilter();
    }

}
