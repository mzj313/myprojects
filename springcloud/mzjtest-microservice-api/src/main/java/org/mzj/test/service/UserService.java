package org.mzj.test.service;

import org.mzj.test.dto.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
public interface UserService {
	
	@RequestMapping(value="/hello1")
	public String hello1(@RequestParam("name") String name);
	
	@RequestMapping(value="/hello2")
	public User hello2(@RequestHeader("name") String name, @RequestHeader("age") Integer age);
	
	@RequestMapping("/hello3")
	public String hello3(@RequestBody User user);
}