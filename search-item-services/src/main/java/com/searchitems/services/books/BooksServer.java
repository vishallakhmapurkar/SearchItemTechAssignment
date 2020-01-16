package com.searchitems.services.books;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(BooksConfiguration.class)
public class BooksServer {


	

	protected Logger logger = Logger.getLogger(BooksServer.class.getName());

	
	public static void main(String[] args) {
		// Tell server to look for accounts-server.properties or
		// accounts-server.yml
		System.setProperty("spring.config.name", "books-server");

		SpringApplication.run(BooksServer.class, args);
	}

}
