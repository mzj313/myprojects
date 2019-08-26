package org.mzj.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//激活DiscoveryClient实现
@EnableEurekaClient
@SpringBootApplication
public class MicroServiceApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(MicroServiceApplication.class).web(true).run(args);
	}
}
