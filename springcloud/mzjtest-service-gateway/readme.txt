测试网关：
http://localhost:8083/api-test/test
http://localhost:8083/api-test/test?accessToken=1

错误：o.s.c.n.z.filters.post.SendErrorFilter : Error during filtering
解决：通过在配置文件中添加
hystrix:
  command:
    default:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 60000