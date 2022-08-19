package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;;

@Controller
public class MainController {

	@PostMapping("/create")
	public String create() {
		return "calendar";
	}

}
