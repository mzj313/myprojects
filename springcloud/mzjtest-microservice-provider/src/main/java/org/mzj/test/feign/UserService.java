package org.mzj.test.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

//继承接口工程的service，对远程服务microservice的访问
@FeignClient("microservice")
public interface UserService extends org.mzj.test.service.UserService {
}
