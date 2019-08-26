package org.mzj.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients    //开启feign支持
@SpringBootApplication
public class WebappApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(WebappApplication.class).web(true).run(args);
	}
}
