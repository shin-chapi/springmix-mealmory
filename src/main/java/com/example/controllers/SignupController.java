package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.User;

;

@Controller
public class SignupController {
	
	@RequestMapping("signup")
	public String signup(@ModelAttribute User user) {
		return "signup";
	}
	
}
	

