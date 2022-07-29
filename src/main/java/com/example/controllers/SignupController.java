package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.User;

;

@Controller
public class SignupController {
	
	@GetMapping("signup")
	public String signup(@ModelAttribute User user) {
		return "signup";
	}
	
	
	@PostMapping("/signup")
    public String signup(@Validated @ModelAttribute User user,
            BindingResult result, Model model) {

       if(result.hasErrors()) {
        	 List<String> errorList = new ArrayList<String>();
             for (ObjectError error : result.getAllErrors()) {
                 errorList.add(error.getDefaultMessage());
             }
             model.addAttribute("validationError", errorList);
            return "signup";
        }
        return "login";
	}
}

