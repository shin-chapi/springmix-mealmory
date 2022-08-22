package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.PostForm;;

@Controller
public class MainController {

	@PostMapping("/post")
	public String post() {
		return "post";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("postform") PostForm postform) {
		return "postEdit";
	}
	
	@PostMapping("/edit")
	public String edit(@ModelAttribute("postform") @Validated PostForm postform, BindingResult bindingResult ) {
		
		// 入力チェック結果
				if (bindingResult.hasErrors()) {
					System.out.println(bindingResult);
					// NG:ユーザー登録画面に戻ります
					return "postEdit";
					
				}
				
				
			
		
		return "calendar";
	}
	
}
