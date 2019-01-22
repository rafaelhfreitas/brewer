package com.bigworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CitiesController {
	
	
	@RequestMapping("/cities/new")
	public String novo() {
		return "city/InsertCity";
	}

}
