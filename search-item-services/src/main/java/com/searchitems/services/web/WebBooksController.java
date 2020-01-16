package com.searchitems.services.web;


import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebBooksController {

	@Autowired
	protected WebBooksService booksService;

	protected Logger logger = Logger.getLogger(WebBooksController.class
			.getName());

	public WebBooksController(WebBooksService accountsService) {
		this.booksService = booksService;
	}

	

	@RequestMapping("/books")
	public String goHome() {
		return "index";
	}

	
}
