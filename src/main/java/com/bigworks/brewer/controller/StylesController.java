package com.bigworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StylesController {	
	
	@RequestMapping("/styles/new")
	public String novo() {
		return "style/InsertStyle";
	}	

}
