###### 项目说明
- mzjtest-config-server    (config) 配置中心
- mzjtest-webapp           (eureka) 服务提供者 test-service
- mzjtest-eureka-server    (eureka) 注册中心
- mzjtest-ribbon-consumer  (ribbon,hystrix,feign) 客户端负载均衡、容错保护、声明式web客户端
- mzjtest-microservice-api () 微服务接口定义和传输对象
- mzjtest-microservice     () 微服务实现
- mzjtest-service-gateway  (zuul,sleuth) 网关、服务治理 gateway-service

###### ribbon
```
ribbon:
  OkToRetryOnAllOperations:true #对所有操作请求都进行重试
  MaxAutoRetries:1              #对当前实例的重试次数
  MaxAutoRetriesNextServer:2    #切换实例的重试次数
```
###### HttpEncodingAutoConfiguration
```
spring:
  http:
    encoding:
      charset:UTF-8
      enable:true
      force:true
```
