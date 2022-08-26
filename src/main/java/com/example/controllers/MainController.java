package com.example.controllers;

import java.time.LocalDateTime;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.form.FileUploadForm;
import com.example.form.PostForm;
import com.example.model.User;
import com.example.service.FileUploadService;
import com.example.service.PostRecordService;;

@Controller
public class MainController {
	
	private final PostRecordService postRecordService;
	
	private final FileUploadService fileUploadService;
	
	public MainController(PostRecordService postRecordService,FileUploadService fileUploadService
			) {
		this.postRecordService = postRecordService;
		this.fileUploadService = fileUploadService;
		
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
	public String edit( @AuthenticationPrincipal User details,
			            @ModelAttribute("postform") @Validated PostForm postform, BindingResult bindingResult,
			            @ModelAttribute("fileUploadForm")  @Validated FileUploadForm file,BindingResult resultFile,
			            @RequestParam MultipartFile image ,Model model) throws Exception{
		
		// 入力チェック結果
				if (bindingResult.hasErrors()|| resultFile.hasErrors()) {
					System.out.println(bindingResult);
					// NG:ユーザー登録画面に戻ります
					return "postEdit";
					
				}
				String imageName = null;
				LocalDateTime dateTime = LocalDateTime.now();
				file.setCreateAt(dateTime);
				imageName = fileUploadService.fileUpload(file,image,null);
				
				postform.setUserName(details.getUsername());
				postform.setCreateAt(dateTime);
				postform.setImageName(imageName);
				postRecordService.insertDiaryRecord(postform);
			
		
		return "calendar";
	}
	
}
