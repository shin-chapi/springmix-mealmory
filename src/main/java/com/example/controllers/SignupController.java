package com.example.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.SignupForm;
import com.example.model.User;
import com.example.repository.UserMapper;
import com.example.service.UserRegisterationService;

@Controller
public class SignupController {
	
	@Autowired
	public UserMapper userMapper;
	
	@Autowired
	public UserRegisterationService UserRegisterationService;
	
	//@Autowired
	public BCryptPasswordEncoder encoder;
	
	/** ユーザー登録画面を表示 */
	@GetMapping("signup")
	public String signup(@ModelAttribute SignupForm form) {
		
		
		// ユーザー登録画面に遷移
		return "signup";
	}
	
	
	/** ユーザー登録処理 */ 
	@PostMapping("signup")

	public String postsignup(@ModelAttribute  @Validated SignupForm form,BindingResult bindingResult, User user) {

		// 入力チェック結果
				if (bindingResult.hasErrors()) { 
					// NG:ユーザー登録画面に戻ります
					return signup(form);
				}
				
				//ログ表示
		Logger logger = LoggerFactory.getLogger(SignupController.class);
		logger.info(form.toString());

		

		UserRegisterationService.registerUser(user);


		
		// ログイン画面にリダイレクト
		return "redirect:/login";
		
		
	}
}

