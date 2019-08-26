package org.mzj.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ClientApplication {
	public static void main(String[] args) {
//		SpringApplication.run(ClientApplication.class,args);
		new SpringApplicationBuilder(ClientApplication.class).web(true).run(args);
	}
}
