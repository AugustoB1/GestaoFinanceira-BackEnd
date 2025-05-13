package com.example.GestaoFinanceira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class GestaoFinanceiraApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoFinanceiraApplication.class, args);
	}

}
