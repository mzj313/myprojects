package org.mzj.test.controller;

import org.mzj.test.dto.User;
import org.mzj.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*@RestController("user")
public class UserController {*/
//改用继承的方式,@RequestMapping会自动带过来
@RestController
public class UserController implements UserService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//@RequestParam里必须填写
	// http://localhost:8081/user/hello1?name=zhang3
	/*@RequestMapping("/hello1")*/
	public String hello1(@RequestParam("name") String name) {
		return "Hello " + name;
	}
	
	/*@RequestMapping("/hello2")*/
	public User hello2(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
		logger.info("==== hello2 -->");
		logger.info("name=" + name + ",age=" + age);
		User user = new User(name, age);
		logger.info("==== hello2 --|");
		return user;
	}
	
	/*@RequestMapping("/hello3")*/
	public String hello3(@RequestBody User user) {
		return "Hello " + user.toString();
	}
}
