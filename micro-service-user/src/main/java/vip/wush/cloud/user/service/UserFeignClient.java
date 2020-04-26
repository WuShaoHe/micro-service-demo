package vip.wush.cloud.user.service;

import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import vip.wush.cloud.user.config.FeignConfiguration;
import vip.wush.cloud.user.model.User;

import java.util.Map;

/**
 * @ClassName: UserFeignClient
 * @Description: spring cloud 对feign进行了增强， 使feign支持了springMVC注解， 并整合了ribbon he eureka,从而让 feign 的使用更加方便
 * @Author: wush
 * @Date: 2020/4/23 17:51
 */

//@FeignClient(name = "micro-service-order", configuration = FeignConfiguration.class) //指定配置类
@FeignClient(name = "micro-service-order")
public interface UserFeignClient {

    /**
     * 指定配置类时，不能使用spring mvc 的注解
     * @return
     */
    @GetMapping("/get")
    String get();


    /**
     * 没有指定 feign 的配置时，不能使用 feign 的注解，
     */
//    @RequestLine("GET /get")
//    String getFor();

    /**
     * Get 请求参数必须加上@RequestParam(name = "user") 注解
     * @param user
     * @return
     */
    @GetMapping("/get/user")
    User getUser(@RequestParam Map<String, Object> user);


    /**
     * Post 请求参数必须加上RequestBody 注解
     * @param user
     * @return
     */
    @PostMapping("/get/user")
    User getUserPost(@RequestBody User user);
}
