package vip.wush.cloud.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.feign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vip.wush.cloud.user.model.User;
import vip.wush.cloud.user.service.UserFeignClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: wush
 * @Date: 2020/4/23 16:06
 */

@RestController
public class UserController {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserFeignClient userFeignClient;


    /**
     * 使用 restTemplate 进行服务调用
     */
    @GetMapping("/get")
    public void getOrderInfo(){
        restTemplate.getForObject("http://micro-service-order/get", String.class);
    }

    /**
     * 使用feign进行服务调用
     * @return
     */
    @GetMapping("/get/order")
    public String getOrderInfoForFeign(){
        return userFeignClient.get();
    }

    /**
     * 使用feign进行服务调用
     * @return
     */
    @GetMapping("/get/user")
    public Object getUserInfoForFeign(){
        Map<String, Object> user = new HashMap<>();
        user.put("name", "哈哈哈");
        user.put("phone", "13800138000");
        user.put("age", 14);

        return userFeignClient.getUser(user);
    }

    @GetMapping("/post/user")
    public Object getUserInfoForFeignPost(){
        User user = new User();
        user.setName("哈哈post");
        user.setPhone("10086");
        user.setAge(235);
        return userFeignClient.getUserPost(user);
    }

    @HystrixCommand(
            fallbackMethod = "getUserFallBack",
            commandProperties =
                    {
                            //隔离策略:1、THREAD —它在单独的线程上执行，并发请求受线程池中线程数的限制 2、SEMAPHORE —它在调用线程上执行，并发请求受信号量限制
                            @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                            //设置超时时间（以毫秒为单位），在此时间之后，调用方将观察到超时并退出命令执行。
                            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
                            //此属性设置统计滚动窗口的持续时间（以毫秒为单位）。Hystrix保持用于断路器使用和发布的指标的时间
                            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1000")
                    },
            threadPoolProperties =
                    {
                            @HystrixProperty(name = "coreSize", value = "1"),
                            @HystrixProperty(name = "maxQueueSize", value = "10")
                    }
    )
    @GetMapping("/hystrix/test")
    public String hystrixTest() throws InterruptedException {
        User user = new User();
        user.setName("hystrix");
        user.setPhone("66666666");
        user.setAge(10);

        return user.toString();
    }


    public String getUserFallBack(){
        User user = new User();
        user.setName("默认用户");
        user.setPhone("0");
        user.setAge(-1);
        return user.toString();
    }

    @GetMapping("user-sever-info")
    public List<ServiceInstance> showInfo(String serverName){
        return this.discoveryClient.getInstances(serverName);
    }

    /**
     * 通过给Zuul调用
     * @return
     */
    @GetMapping("/zuul/get/user")
    public Object getUserInfoForZuul(){
        User user = new User();
        user.setName("我是一个用户");
        user.setPhone("13800138000");
        user.setAge(14);
        return user;
    }

}
