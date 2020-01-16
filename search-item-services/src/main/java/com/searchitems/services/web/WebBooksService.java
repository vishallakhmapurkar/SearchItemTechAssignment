package com.searchitems.services.web;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.searchitems.services.books.Book;


@Service
public class WebBooksService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebBooksService.class
			.getName());

	public WebBooksService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}

	
	@PostConstruct
	public void demoOnly() {
		// Can't do this in the constructor because the RestTemplate injection
		// happens afterwards.
		logger.warning("The RestTemplate request factory is "
				+ restTemplate.getRequestFactory().getClass());
	}

	public String findByName(String name) {

		logger.info("findByName() invoked: for " + name);
		return restTemplate.getForObject(serviceUrl + "/books/{name}",
				String.class, name);
	}

	public List<Book> byBooksContains(String name) {
		logger.info("byBooksContains() invoked:  for " + name);
		Book[] books = null;

		try {
			books = restTemplate.getForObject(serviceUrl
					+ "/accounts/owner/{name}", Book[].class, name);
		} catch (HttpClientErrorException e) { // 404
			// Nothing found
		}

		if (books == null || books.length == 0)
			return null;
		else
			return Arrays.asList(books);
	}

	
}
