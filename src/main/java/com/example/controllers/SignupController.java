package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.User;
import com.example.repository.UserRepository;

;

@Controller
public class SignupController {
	
	@Autowired
	private  UserRepository repository;
	
	@GetMapping("signup")
	public String signup(@ModelAttribute User user) {
		return "signup";
	}
	
	@PostMapping("/signup")
    public String signup(@Validated @ModelAttribute User user,
            BindingResult result, Model model) {
		
		
       if(result.hasErrors()) {
//        	 
            return "signup";
        }
       repository.save(user);
        return "login";
	}
}
	

