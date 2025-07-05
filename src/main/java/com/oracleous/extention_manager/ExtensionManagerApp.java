package com.oracleous.extention_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ExtensionManagerApp {
	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(ExtensionManagerApp.class, args);
	}

}

