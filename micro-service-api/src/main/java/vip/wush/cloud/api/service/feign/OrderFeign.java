package vip.wush.cloud.api.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.wush.cloud.api.model.Order;

import java.util.Map;

/**
 * @ClassName: OrderFeign
 * @Description: order feign
 * @Author: wush
 * @Date: 2020/4/25 15:09
 */
@Component
@FeignClient(name = "micro-service-order")
public interface OrderFeign {

    @GetMapping("/zuul/order/get")
    Order getOrder(@RequestParam Map<String, Object> user);

}
