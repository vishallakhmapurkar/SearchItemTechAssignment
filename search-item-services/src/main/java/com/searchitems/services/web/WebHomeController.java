package com.searchitems.services.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WebHomeController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}

}
