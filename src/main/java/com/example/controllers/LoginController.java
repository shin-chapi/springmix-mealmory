package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@PostMapping("/calendar")
    public String login(Model model) {
		System.out.println("calendar");
		return "calendar";
		
	}
	@RequestMapping("/logout")
    public String logout() {
		System.out.println("calendar");
		return "logout";
		
	}

}
