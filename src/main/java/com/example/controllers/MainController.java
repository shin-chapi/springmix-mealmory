package com.example.controllers;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.form.PostForm;
import com.example.model.Post;
import com.example.model.User;
import com.example.service.PostRecordService;

@Controller
public class MainController {

	@Value("${image.local:false}")
	private String imageLocal;

	private final PostRecordService postRecordService;
	
	@Autowired
	private static HttpServletRequest request;

	public MainController(PostRecordService postRecordService) {
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
	public String edit(Principal principal,User user ,@ModelAttribute("postform") @Validated PostForm postform,
			BindingResult bindingResult, @RequestParam MultipartFile image, RedirectAttributes redirAttrs)
			throws IOException {

		// 入力チェック結果
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			// NG:ユーザー登録画面に戻ります
			return "postEdit";

		}
		 boolean isImageLocal = false;
	        if (imageLocal != null) {
	            isImageLocal = new Boolean(imageLocal);
	        }

	        Post entity = new Post();
//	        Authentication authentication = (Authentication) principal;
//	        User user = (User) authentication.getPrincipal();
	        entity.setId(user.getId());
	        File destFile = null;
	        if (isImageLocal) {
	            destFile = saveImageLocal(image, entity);
	            entity.setPath(destFile.getAbsolutePath());
	        } else {
	            entity.setPath("");
	        }
	        entity.setMemo(postform.getMemo());
	     
	        
	        postRecordService.insertDiaryRecord(entity);

	        redirAttrs.addFlashAttribute("hasMessage", true);
	        redirAttrs.addFlashAttribute("class", "alert-info");
	        redirAttrs.addFlashAttribute("message", "投稿に成功しました。");

	    	return "calendar";
	    }

	    private File saveImageLocal(MultipartFile image, Post entity) throws IOException {
	        File uploadDir = new File("/uploads");
	        uploadDir.mkdir();

	        String uploadsDir = "/uploads/";
	        String realPathToUploads = request.getServletContext().getRealPath(uploadsDir);
	        if (!new File(realPathToUploads).exists()) {
	            new File(realPathToUploads).mkdir();
	        }
	        String fileName = image.getOriginalFilename();
	        File destFile = new File(realPathToUploads, fileName);
	        image.transferTo(destFile);

	        return destFile;
	    }

	

		

	
	}


