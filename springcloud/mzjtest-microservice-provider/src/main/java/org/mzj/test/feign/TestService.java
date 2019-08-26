package org.mzj.test.feign;

import org.mzj.test.dto.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//封装对远程服务microservice的访问
@FeignClient(name="microservice",fallback=TestServiceFallback.class)
public interface TestService {
	@RequestMapping("/test")
	public String test();
	
	//参数中必须加上注解
	//注：@RequestParam()中必须填写，否则报IllegalStateException: RequestParam.value() was empty on parameter
	@RequestMapping(value="/user/hello1")
	public String hello1(@RequestParam("name") String name);
	
	//注：@@RequestHeader()中必须填写，否则报IllegalStateException: RequestHeader.value() was empty on parameter
	@RequestMapping(value="/user/hello2")
	public User hello2(@RequestHeader("name") String name, @RequestHeader("age") Integer age);
	
	@RequestMapping("/user/hello3")
	public String hello3(@RequestBody User user);
}
