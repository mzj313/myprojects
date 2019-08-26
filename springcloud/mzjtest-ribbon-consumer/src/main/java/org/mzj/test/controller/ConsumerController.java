package org.mzj.test.controller;

import org.mzj.test.service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ConsumerService consumerService;
	
	//http://localhost:9000/test
	@RequestMapping("test")
	public String doTest() {
		// 必须改造成去service里调用，直接在Controller里测试熔断失败
		long begin = System.currentTimeMillis();
		logger.info("====test...");
		
		String result = consumerService.doTestInternal();
		
		long end = System.currentTimeMillis();
		logger.info("====test. cost " + (end-begin) + "ms");
		return result;
	}
	
	// http://localhost:9000/trace1
	@RequestMapping("trace1")
	public String doTrace() {
		long begin = System.currentTimeMillis();
		logger.info("====trace...");
		
		String result = consumerService.doTraceInternal();
		
		long end = System.currentTimeMillis();
		logger.info("====trace. cost " + (end-begin) + "ms");
		return result;
	}
}
