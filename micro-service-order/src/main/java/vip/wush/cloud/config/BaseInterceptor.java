package vip.wush.cloud.config;

import org.apache.tomcat.util.buf.MessageBytes;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.plugin.com.Dispatch;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BaseInterceptor
 * @Description: 拦截器
 * @Author: wush
 * @Date: 2020/4/23 23:21
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

//        Object a = findCoyoteRequest(httpServletRequest);
//        Field coyoteRequest = a.getClass().getDeclaredField("coyoteRequest");
//        coyoteRequest.setAccessible(true);
//        Object b = coyoteRequest.get(a);
//        Field uriMB = b.getClass().getDeclaredField("uriMB");
//        uriMB.setAccessible(true);
//        MessageBytes c = (MessageBytes)uriMB.get(b);
//        System.out.println("uriMB=== "+ c.getString());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    //根据Field获得对应的Class
    private Class getClassByName(Class classObject, String name){
        Map<Class, List<Field>> fieldMap = new HashMap<>();
        Class returnClass = null;
        Class tempClass = classObject;
        while (tempClass != null) {
            fieldMap.put(tempClass, Arrays.asList(tempClass .getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }

        for(Map.Entry<Class,List<Field>> entry: fieldMap.entrySet()){
            for (Field f : entry.getValue()) {
                if(f.getName().equals(name)){
                    returnClass = entry.getKey();
                    break;
                }
            }
        }
        return returnClass;
    }

    //递归遍历父类寻找coyoteRequest Field
    private Object findCoyoteRequest(Object request) throws NoSuchFieldException, IllegalAccessException {
        Class a = getClassByName(request.getClass(),"request");
        Field request1 = a.getDeclaredField("request");
        request1.setAccessible(true);
        Object b = request1.get(request);
        if(getClassByName(b.getClass(),"coyoteRequest") == null){
            return findCoyoteRequest(b);
        }else{
            return b;
        }
    }

}
