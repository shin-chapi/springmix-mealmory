package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.PostForm;
import com.example.model.User;
import com.example.service.PostRecordService;;

@Controller
public class MainController {
	
	private final PostRecordService postRecordService;
	
	public MainController(PostRecordService postRecordService
			) {
		this.postRecordService = postRecordService;
		
	}

	@PostMapping("/post")
	public String post() {
		return "post";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("postform") PostForm postform) {
		return "postEdit";
	}
	
	@PostMapping("/edit")
	public String edit(User user, @ModelAttribute("postform") @Validated PostForm postform, BindingResult bindingResult ) {
		
		// 入力チェック結果
				if (bindingResult.hasErrors()) {
					System.out.println(bindingResult);
					// NG:ユーザー登録画面に戻ります
					return "postEdit";
					
				}
				
				postRecordService.insertDiaryRecord(postform);
			
		
		return "calendar";
	}
	
}
