package com.ramenshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootRamenShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRamenShopApplication.class, args);
	}

}
