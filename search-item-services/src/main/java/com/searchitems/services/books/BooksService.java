package com.searchitems.services.books;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BooksService {
	@Autowired
	private Environment env;

	protected Logger logger = Logger.getLogger(BooksService.class.getName());

	

	
	public GBWrapper byNameAndMaxLimit(String q,Integer maxResults) {
		String url = UriComponentsBuilder.fromHttpUrl(env.getProperty("google.bookapiurl"))
				.queryParam("q", q).queryParam("maxResults", maxResults).queryParam("key", env.getProperty("google.apikey")).toUriString();
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Respose got");
		return restTemplate.getForObject(url, GBWrapper.class);
	}

	
}
