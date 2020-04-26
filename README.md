# micro-service-demo
### 使用spring cloud 构建微服务demo  

#### Eureka 服务的注册与发现  
#### Ribbon 客户端侧的负载均衡  
#### Feign  实行声明式 RESTFUL 调用  
#### Hystrix 实现服务的容错处理  
#### Zuul   构建微服务网关  
#### config 统一管理服务配置  
#### Sleuth 实现微服务的链路跟踪  


Eureka: 单实例 ——>多实例（高可用）    
Ribbon：1. 通过eureka获取服务列表  2. 配置属性：listOfServers 自定义服务列表   
        负载均衡：1.通过在RestTemplate上注解@LoadBalanced实现负载均衡  2. 结合 feign 实现负载均衡  
Feign：只要classpath 存在Ribbon 就会自动整合， 只要classpath 存在Hystrix 也会自动整合  
Hystrix：1. Hystrix的监控：http://host:port/Hystrix.stream 2. 可视化监控数据：HystrixDashborad 3. 使用Turbine 聚合监控数据 4. 使用消息中间件收集数据：Rabbit  
Sleuth：整合了ELK框架，方便整个链路的跟踪查找  


#### 常用注解：   
Eureka: @EnableEurekaServer 服务端  @EnableDiscoveryClient 或 @EnableEurekaClient 客户端  
Ribbon：@LoadBalanced 使被注解的RestTemplate 具有负载均衡， @RibbonClient 为指定name的Ribbon client自定义配置； 通过注入 IRle 接口的不同实现实现，可以修改负载均衡的规则，如随机（new RondomRule()）;    
Feign: @EnableFeignClients  @FeignClient  @RequestLine  
Hystrix: @EnableHystrix  @HystrixCommand   @HystrixProperty  
Dashboard: @EnableHystrixDashboard  
Turbine: @EnableTurbine @EnableTurbineStream  
