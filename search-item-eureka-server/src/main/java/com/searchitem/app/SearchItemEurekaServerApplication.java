package com.searchitem.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class SearchItemEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchItemEurekaServerApplication.class, args);
	}

}
