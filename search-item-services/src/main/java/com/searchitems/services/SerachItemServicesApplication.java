package com.searchitems.services;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan("com.searchitems")
@SpringBootApplication
public class SerachItemServicesApplication {
	protected Logger logger = Logger.getLogger(SerachItemServicesApplication.class.getName());

	
	public static void main(String[] args) {
		// Tell server to look for accounts-server.properties or
		
		System.setProperty("spring.config.name", "search-items");

		SpringApplication.run(SerachItemServicesApplication.class, args);
	}}
