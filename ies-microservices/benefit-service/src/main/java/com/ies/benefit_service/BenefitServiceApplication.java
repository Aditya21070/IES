package com.ies.benefit_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BenefitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BenefitServiceApplication.class, args);
	}

}
