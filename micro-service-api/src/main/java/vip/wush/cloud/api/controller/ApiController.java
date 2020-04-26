package vip.wush.cloud.api.controller;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;
import vip.wush.cloud.api.service.ApilService;

import java.util.HashMap;

/**
 * @ClassName: ApiController
 * @Description: TODO
 * @Author: wush
 * @Date: 2020/4/25 15:04
 */

@RestController
public class ApiController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private ApilService apilService;

    @GetMapping("/create")
    public Object createOrder(){
        return apilService.createOrder();
    }


    @GetMapping("/aggregate")
    public DeferredResult<HashMap<String, Object>> aggregate() {
        Observable<HashMap<String, Object>> result = this.aggregateObservable();
        return this.toDeferredResult(result);
    }

    public Observable<HashMap<String, Object>> aggregateObservable() {
        // 合并两个或者多个Observables发射出的数据项，根据指定的函数变换它们
        return Observable.zip(
                this.apilService.getUser(),
                this.apilService.getOrder(),
                (user, order) -> {
                    HashMap<String, Object> map = Maps.newHashMap();
                    map.put("user", user);
                    map.put("order", order);
                    return map;
                }
        );
    }

    public DeferredResult<HashMap<String, Object>> toDeferredResult(Observable<HashMap<String, Object>> details) {
        DeferredResult<HashMap<String, Object>> result = new DeferredResult<>();
        // 订阅
        details.subscribe(new Observer<HashMap<String, Object>>() {
            @Override
            public void onCompleted() {
                LOGGER.info("完成...");
            }

            @Override
            public void onError(Throwable throwable) {
                LOGGER.error("发生错误...", throwable);
            }

            @Override
            public void onNext(HashMap<String, Object> stringObjectHashMap) {
                result.setResult(stringObjectHashMap);
            }
        });

        return result;
    }
}
