package com.example.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.SignupForm;

@Controller
public class SignupController {
	
	@GetMapping("signup")
	public String signup(@ModelAttribute SignupForm form) {
		return "signup";
	}
	
	@PostMapping("signup")
	public String postsignup(@ModelAttribute  @Validated SignupForm form ,BindingResult bindingResult) {
		// 入力チェック結果
				if (bindingResult.hasErrors()) { 
					// NG:ユーザー登録画面に戻ります
					return signup(form);
				}
		Logger logger = LoggerFactory.getLogger(SignupController.class);
		logger.info(form.toString());
		return "redirect:/login";
		
		
	}
}

