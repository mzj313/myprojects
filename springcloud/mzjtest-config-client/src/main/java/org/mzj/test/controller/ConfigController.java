package org.mzj.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//config客户端
@RefreshScope
@RestController
public class ConfigController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${url}")
	private String url;
	
	@Autowired
	private Environment env;
	
	// http://localhost:7002/config
	@RequestMapping("/config")
	public String config() {
		logger.info("==== getConfig -->");
		logger.info("url=" + url);
		
		logger.info("url=" + env.getProperty("url", "undefined"));
		//获取通过属性覆盖配置的全局默认属性
		logger.info("author=" + env.getProperty("author", "undefined"));
		
		return this.url;
	}
}
