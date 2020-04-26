package vip.wush.cloud.api.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.wush.cloud.api.model.User;

import java.util.Map;

/**
 * @ClassName: UserFeign
 * @Description: user feign
 * @Author: wush
 * @Date: 2020/4/25 15:10
 */
@Component
@FeignClient(name = "micro-service-user")
public interface UserFeign {

    @GetMapping("/zuul/get/user")
    User getUser(@RequestParam Map<String, Object> user);


}
