package com.bigworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {

	@RequestMapping("/users/new")
	public String novo() {
		return "user/InsertUser";
	}
}
