package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.model.User;

;

@Controller
public class SignupController {
	
	

	
	@GetMapping("signup")
	public String signup(@ModelAttribute User user) {
		return "signup";
	}
	
}
	

