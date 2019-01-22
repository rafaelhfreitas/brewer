package com.bigworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomersController {
	
	
	@RequestMapping("/customers/new")
	public String novo() {
		return "customer/InsertCustomer";
	}
    
    	

}
