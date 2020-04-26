package vip.wush.cloud.api.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import vip.wush.cloud.api.model.Order;
import vip.wush.cloud.api.model.User;
import vip.wush.cloud.api.service.ApilService;
import vip.wush.cloud.api.service.feign.OrderFeign;
import vip.wush.cloud.api.service.feign.UserFeign;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ApiServiceImpl
 * @Description: TODO
 * @Author: wush
 * @Date: 2020/4/25 15:07
 */
@Service
public class ApiServiceImpl implements ApilService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private OrderFeign orderFeign;


    @Override
    public Object createOrder() {

        Map<String, Object> map = new HashMap<String, Object>();

        //获取用户信息
        User user = userFeign.getUser(map);
//        User user1 = restTemplate.getForObject("http://micro-service-user/zuul/get/user", User.class);
        //获取商品信息

        //创建订单信息
        Order order = orderFeign.getOrder(map);
//        Order order1 = restTemplate.getForObject("http://micro-service-order/zuul/order/get", Order.class);

        return "订单创建成功";
    }


    @HystrixCommand(fallbackMethod = "userFallback")
    public Observable<User> getUser() {
        Map<String, Object> map = new HashMap<String, Object>();
        // 创建一个被观察者
        return Observable.create(observer -> {
            // 请求用户微服务的/{id}端点
            User user = userFeign.getUser(map);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    @HystrixCommand(fallbackMethod = "orderFallback")
    public Observable<Order> getOrder() {
        Map<String, Object> map = new HashMap<String, Object>();
        return Observable.create(observer -> {
            // 请求电影微服务的/user/{id}端点
            Order order = orderFeign.getOrder(map);
            observer.onNext(order);
            observer.onCompleted();
        });
    }

    public User userFallback (){
        User user = new User();
        user.setName("默认用户");
        user.setAge(-1);
        user.setPhone("888888888888");
        return user;
    }

    public Order orderFallback (){
        Order order = new Order();
        order.setStatus(-1);
        order.setId(0L);
        order.setNumber("888888888888");
        return order;
    }
}
