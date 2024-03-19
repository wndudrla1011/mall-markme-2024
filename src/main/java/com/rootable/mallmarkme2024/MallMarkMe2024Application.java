package com.rootable.mallmarkme2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MallMarkMe2024Application {

	public static void main(String[] args) {
		SpringApplication.run(MallMarkMe2024Application.class, args);
	}

}
