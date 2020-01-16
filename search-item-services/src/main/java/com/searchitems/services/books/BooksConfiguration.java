package com.searchitems.services.books;

import java.util.logging.Logger;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.searchitems.services.books")
public class BooksConfiguration {

	protected Logger logger;

	public BooksConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

}
