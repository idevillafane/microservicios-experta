package com.expertamicroservices.comercialservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ComercialServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComercialServiceApplication.class, args);
	}

}
