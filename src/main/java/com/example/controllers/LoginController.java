package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		System.out.println("login");
		return "login";
	}

	@GetMapping("/logout")
	public String logout() {
		System.out.println("logout");
		return "logout";

	}

	@GetMapping("/calendar")
	public String calendar() {
		return "calendar";
	}

	@RequestMapping("/")
	public String index() {
		return "calendar";
	}

}
