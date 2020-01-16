package com.searchitems.services.books;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BooksHomeController {
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}

}
