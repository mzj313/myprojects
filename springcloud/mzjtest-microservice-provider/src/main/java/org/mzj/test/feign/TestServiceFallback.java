package org.mzj.test.feign;

import org.mzj.test.dto.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

// 服务降级
@Component
public class TestServiceFallback implements TestService {

	@Override
	public String test() {
		return "test error";
	}

	public String hello1(@RequestParam("name") String name) {
		return "hello1 error";
	}

	public User hello2(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
		return new User("未知", 0);
	}

	public String hello3(@RequestBody User user) {
		return "hello3 error";
	}
}
