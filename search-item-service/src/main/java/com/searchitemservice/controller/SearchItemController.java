package com.searchitemservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class SearchItemController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Environment env;
	@RequestMapping("/")
	public String home() {
		// This is useful for debugging
		// When having multiple instance of gallery service running at different ports.
		// We load balance among them, and display which instance received the request.
		return "Hello from Gallery Service running at port: " + env.getProperty("local.server.port");
	}
	
	@RequestMapping("/{q}")
	public String getGallery(@PathVariable final String q) {
		String res= restTemplate.getForObject("http://book-service/book/", String.class);
	
		return res;
	}
}
