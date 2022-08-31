package com.example.controllers;

import java.time.LocalDateTime;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.FileUploadForm;
import com.example.form.PostForm;
import com.example.model.User;
import com.example.model.enums.PostRecordCategory;
import com.example.service.FileUploadService;
import com.example.service.PostRecordService;;

@Controller
public class MainController {

	private final PostRecordService postRecordService;

	private final FileUploadService fileUploadService;
	
	final static String s3Path = "salmon-springmix-mealmory";
	
//	private final static String localPath = "private/var/upload/";

	public MainController(PostRecordService postRecordService, FileUploadService fileUploadService) {
		this.postRecordService = postRecordService;
		this.fileUploadService = fileUploadService;

	}

	@PostMapping("/post")
	public String post() throws Exception{
		
		return "post";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("postform") PostForm postform,
			@ModelAttribute("fileUploadForm") FileUploadForm file, Model model) {
		model.addAttribute("lists", PostRecordCategory.values());
		return "postEdit";
	}

	@PostMapping("/edit")
	public String edit(@AuthenticationPrincipal User details, 
			           @ModelAttribute("postform") @Validated PostForm postform,BindingResult bindingResult, 
			           @ModelAttribute("fileUploadForm") @Validated FileUploadForm file,BindingResult resultFile, 
			           Model model) throws Exception {

		// 入力チェック結果
		if (bindingResult.hasErrors() || resultFile.hasErrors()) {
			model.addAttribute("lists", PostRecordCategory.values());
			System.out.println(bindingResult);
			// NG:ユーザー登録画面に戻ります
			return "postEdit";

		}

		PostForm exist = postRecordService.findOneDiaryRecord(details.getUsername(), postform.getCategoryId(),
				postform.getDiaryDay());
		if (exist != null) {
			model.addAttribute("lists", PostRecordCategory.values());
			model.addAttribute("message", "既に同じカテゴリ、同じ日付で登録されています");
			return "postEdit";
		}

		String imageName = null;
		LocalDateTime dateTime = LocalDateTime.now();
			if(file == null) {
				System.out.println("nullです");
			}
		// ファイルが空でない場合に、ファイルの中身をチェックする
		if (!file.getMultipartFile().isEmpty()) {
			if(fileUploadService.fileValid(file)) {
			file.setCreateAt(dateTime);
			imageName = fileUploadService.fileUpload(file,s3Path , null);
			}else {
				model.addAttribute("lists", PostRecordCategory.values());
				model.addAttribute("message","ファイル形式が不正です");
				return "postEdit";
			}
		}
		postform.setUserName(details.getUsername());
		postform.setCreateAt(dateTime);
		postform.setImageName(imageName);
		postRecordService.insertDiaryRecord(postform);

		return "calendar";
	}

}
