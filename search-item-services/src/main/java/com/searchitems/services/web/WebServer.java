package com.searchitems.services.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false) // Disable component scanner
public class WebServer {

	
	public static final String BOOKS_SERVICE_URL = "http://BOOKS-SERVICE";


	public static void main(String[] args) {
		// Tell server to look for web-server.properties or web-server.yml
		System.setProperty("spring.config.name", "web-server");
		SpringApplication.run(WebServer.class, args);
	}

	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	
	@Bean
	public WebBooksService booksService() {
		return new WebBooksService(BOOKS_SERVICE_URL);
	}

	
	@Bean
	public WebBooksController booksController() {
		return new WebBooksController(booksService());
	}

	@Bean
	public WebHomeController homeController() {
		return new WebHomeController();
	}
}
