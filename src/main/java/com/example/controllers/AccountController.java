package com.example.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.form.SignupForm;
import com.example.model.User;
import com.example.service.UserEditService;



@Controller
public class AccountController {
	
	
	
	private final UserEditService userEditService;
	
	public  AccountController (UserEditService userEditService) {
		this.userEditService =  userEditService;
	}
	
	@GetMapping("/mypage")
	public String indexUserPage(@AuthenticationPrincipal User details,
			                    Model model) {
		return "index";
	}
	//ユーザーを編集
	@GetMapping("/index/mypage/confirm")
	public String confirmUserPage(@AuthenticationPrincipal User details,
			                      Model model) {
		SignupForm form = new SignupForm();
		form.setName(details.getUsername());
		model.addAttribute("ConfirmDelete", form);
		return "accountDelete";
	}
	
	//ユーザーを削除
	@PostMapping("/index/mypage/confirm/delete")
	public String deleteUser(@ModelAttribute("AccountInfoForm") User form,
			                    RedirectAttributes model){
		userEditService.deleteUser(form.getName());
		return "redirect:/login";
	}

}
