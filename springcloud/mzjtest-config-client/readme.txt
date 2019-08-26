动态刷新：
1.引入spring-boot-starter-actuator依赖
2.修改config-server里cmsdb-dev.properties中的值
3.POST http://localhost:7002/refresh
4.再次访问http://localhost:7002/config看到值已经变化
