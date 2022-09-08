package com.example.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.model.User;



@Controller
public class AccountController {
	
	@GetMapping("/mypage")
	public String showIndexMyPage(@AuthenticationPrincipal User details,
			                                                Model model) {
		
		
		
		return "index";
	}
	

}
