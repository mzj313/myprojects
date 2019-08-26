package org.mzj.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ConsumerService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "fallback")
	public String doTestInternal() {
		return restTemplate.getForEntity("http://microservice/test", String.class).getBody();
	}
	
	@HystrixCommand(fallbackMethod = "fallback")
	public String doTraceInternal() {
		return restTemplate.getForEntity("http://gateway-service/trace1", String.class).getBody();
	}

	// 熔断请求回调方法
	public String fallback() {
		logger.info("fallback");
		return "error";
	}
}
