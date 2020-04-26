package vip.wush.cluoud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @ClassName: board
 * @Description: hystrix监控看板 app
 * @Author: wush
 * @Date: 2020/4/24 22:52
 */

@SpringBootApplication
@EnableHystrixDashboard
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);

    }
}
