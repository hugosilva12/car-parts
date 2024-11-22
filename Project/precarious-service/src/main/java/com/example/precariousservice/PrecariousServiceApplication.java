package com.example.precariousservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PrecariousServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrecariousServiceApplication.class, args);
	}

}
