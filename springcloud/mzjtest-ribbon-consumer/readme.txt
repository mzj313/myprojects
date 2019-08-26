http://localhost:9000/test

SpringCLoud中的“Discovery Service”有多种实现，比如：eureka, consul, zookeeper。
1，@EnableDiscoveryClient注解是基于spring-cloud-commons依赖，并且在classpath中实现； 
2，@EnableEurekaClient注解是基于spring-cloud-netflix依赖，只能为eureka作用；

问题：IllegalStateException: RequestParam.value() was empty on parameter 0
解决：@RequestParam()中必须填写

-----------------------------------------------------
Spring Cloud Ribbon: 用以实现客户端负载均衡，核心有三点：
    服务发现，发现依赖服务的列表;
    服务选择规则，在多个服务中如何选择一个有效服务;
    服务监听，检测失效的服务，高效剔除失效服务
Netflix Hystrix：容错管理工具，旨在通过控制服务和第三方库的节点,从而对延迟和故障提供更强大的容错能力。