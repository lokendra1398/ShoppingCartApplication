package com.codebuffer.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ProductService1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProductService1Application.class, args);
	}

}
