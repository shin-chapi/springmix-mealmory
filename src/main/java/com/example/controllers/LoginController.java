package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
//	@PostMapping("/login")
//    public String login(Model model) {
//		System.out.println("calendar");
//		return "calendar";
		
	
	
	 @GetMapping("/login")
	    public String login() {
	    	System.out.println("login");
	    	return "login";
	    }
	@RequestMapping("/logout")
    public String logout() {
		System.out.println("logout");
		return "logout";
		
	}

}
