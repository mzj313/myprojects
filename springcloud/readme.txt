mzjtest-config-server    (config) 配置中心
mzjtest-webapp    (eureka) 服务提供者 test-srevice
mzjtest-eureka-server    (eureka) 注册中心
mzjtest-ribbon-consumer  (ribbon,hystrix,feign) 客户端负载均衡、容错保护、声明式web客户端
mzjtest-microservice-api () 微服务接口定义和传输对象
mzjtest-microservice     () 微服务实现
mzjtest-service-gateway  (zuul,sleuth) 网关、服务治理 gateway-service


spring cloud子项目包括：
Spring Cloud Config：配置管理开发工具包，可以让你把配置放到远程服务器，目前支持本地存储、Git以及Subversion。
Spring Cloud Bus：事件、消息总线，用于在集群（例如，配置变化事件）中传播状态变化，可与Spring Cloud Config联合实现热部署。
Spring Cloud Netflix：针对多种Netflix组件提供的开发工具包，其中包括Eureka、Hystrix、Zuul、Archaius等。
Netflix Eureka：云端负载均衡，一个基于 REST 的服务，用于定位服务，以实现云端的负载均衡和中间层服务器的故障转移。
Netflix Hystrix：容错管理工具，旨在通过控制服务和第三方库的节点,从而对延迟和故障提供更强大的容错能力。
Netflix Zuul：边缘服务工具，是提供动态路由，监控，弹性，安全等的边缘服务。
Netflix Archaius：配置管理API，包含一系列配置管理API，提供动态类型化属性、线程安全配置操作、轮询框架、回调机制等功能。
Spring Cloud for Cloud Foundry：通过Oauth2协议绑定服务到CloudFoundry，CloudFoundry是VMware推出的开源PaaS云平台。
Spring Cloud Sleuth：日志收集工具包，封装了Dapper,Zipkin和HTrace操作。
Spring Cloud Data Flow：大数据操作工具，通过命令行方式操作数据流。
Spring Cloud Security：安全工具包，为你的应用程序添加安全控制，主要是指OAuth2。
Spring Cloud Consul：封装了Consul操作，consul是一个服务发现与配置工具，与Docker容器可以无缝集成。
Spring Cloud Zookeeper：操作Zookeeper的工具包，用于使用zookeeper方式的服务注册和发现。
Spring Cloud Stream：数据流操作开发包，封装了与Redis,Rabbit、Kafka等发送接收消息。
Spring Cloud CLI：基于 Spring Boot CLI，可以让你以命令行方式快速建立云组件。

Spring Cloud Ribbon: 用以实现客户端负载均衡，核心有三点：服务发现，发现依赖服务的列表;服务选择规则，在多个服务中如何选择一个有效服务;服务监听，检测失效的服务，高效剔除失效服务
Spring Cloud Feign: 整合了ribbon和hystrix，提供一种声明式web服务客户端定义方式。
---------------------------------------------------------
ribbon:
  OkToRetryOnAllOperations:true #对所有操作请求都进行重试
  MaxAutoRetries:1              #对当前实例的重试次数
  MaxAutoRetriesNextServer:2    #切换实例的重试次数

#HttpEncodingAutoConfiguration
spring:
  http:
    encoding:
      charset:UTF-8
      enable:true
      force:true
