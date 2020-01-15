package com.bookservice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
@EnableAutoConfiguration 
@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.bookservice")
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
	    return builder.build();
	}
}
