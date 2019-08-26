package org.mzj.test.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONObject;

@RestController
public class TestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DiscoveryClient client;

	// http://localhost:8081/test
	@RequestMapping("/test")
	public String test(@RequestHeader("User-Agent") String userAgent,
			@RequestHeader(value="Content-Type",defaultValue="text/html; charset=utf-8") String contentType) {
		logger.info("==== test -->");
		ServiceInstance instance = client.getLocalServiceInstance();
		String json = JSONObject.fromObject(instance).toString();
		logger.info("LocalServiceInstance: " + json);
		logger.info("User-Agent: " + userAgent + ", Content-Type: " + contentType);
		
		// 模拟阻塞，时间要超过Hystrix默认的超时时间1000ms,参考HystrixCommandProperties
		try {
			int sleepTime = new Random().nextInt(2000);
			logger.info("====sleepTime: " + sleepTime + "ms");
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("==== test --|");
		return json;
	}
}
