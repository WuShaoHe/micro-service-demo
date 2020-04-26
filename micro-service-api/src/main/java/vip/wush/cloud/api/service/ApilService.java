package vip.wush.cloud.api.service;

import rx.Observable;
import vip.wush.cloud.api.model.Order;
import vip.wush.cloud.api.model.User;

/**
 * @ClassName: ZuulService
 * @Description: TODO
 * @Author: wush
 * @Date: 2020/4/25 15:06
 */

public interface ApilService {


    Object createOrder();


    Observable<User> getUser();


    Observable<Order> getOrder();

}
