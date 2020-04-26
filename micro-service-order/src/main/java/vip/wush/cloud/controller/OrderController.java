package vip.wush.cloud.controller;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import vip.wush.cloud.model.Order;
import vip.wush.cloud.model.User;

import java.util.Map;


/**
 * @ClassName: OrderController
 * @Description: TODO
 * @Author: wush
 * @Date: 2020/4/23 16:29
 */

@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Value("${server.port}")
    private String port;

    @GetMapping("/")
    public Object getOrder(){
        System.out.println("端口：" + port + ", 获取订单信息");
        return "获取商品信息成功";
    }

    @GetMapping("/get")
    public Object getOrderInfo(){
        System.out.println("端口：" + port + ", 获取订单信息");
        return "获取商品信息成功";
    }

    @GetMapping("/get/user")
    public Object getUserInfo(@RequestParam String name,
                              @RequestParam String phone,
                              @RequestParam int age){

        System.out.println("端口：" + port + ", 获取用户信息");
        User user = new User();
        user.setName("渣渣");
        user.setPhone("13049693360");
        user.setAge(11);
        return user;
    }

    @PostMapping("/get/user")
    public Object getUserPost(@RequestBody User userP){
        System.out.println(userP.toString());
        System.out.println("端口：" + port + ", 获取用户信息");
        User user = new User();
        user.setName("渣渣");
        user.setPhone("13049693360");
        user.setAge(11);
        return user;
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
    @GetMapping("/order/get")
    public Object get(Long id){

        System.out.println("端口：" + port + ", 获取订单信息， id=" + id);
        User user = new User();
        user.setName("渣渣");
        user.setPhone("13049693360");
        user.setAge(11);
        return user;
    }


    public Object getUserFallBack(Long id){
        System.out.println("端口：" + port + ", 获取订单信息失败， id=" + id);
        User user = new User();
        user.setName("默认用户");
        user.setPhone("0");
        user.setAge(-1);
        return user;
    }



    @GetMapping("/zuul/order/get")
    public Object getOrderForZuul(){
        System.out.println("端口：" + port + ", 获取订单信息");
        Order order = new Order();
        order.setId(1L);
        order.setNumber("1234567890");
        order.setStatus(1);
        return order;
    }


}
