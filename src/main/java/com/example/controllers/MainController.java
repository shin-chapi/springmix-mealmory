package com.example.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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



	public MainController(PostRecordService postRecordService, FileUploadService fileUploadService) {
		this.postRecordService = postRecordService;
		this.fileUploadService = fileUploadService;

	}

	@PostMapping("/post")
	public String post() throws Exception {

		return "post";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("postform") PostForm postform,
			@ModelAttribute("fileUploadForm") FileUploadForm file, Model model) {
		model.addAttribute("lists", PostRecordCategory.values());
		return "postEdit";
	}

	@PostMapping("/edit")
	public String edit(@AuthenticationPrincipal User details, @ModelAttribute("postform") @Validated PostForm postform,
			BindingResult bindingResult, @ModelAttribute("fileUploadForm") @Validated FileUploadForm file,
			BindingResult resultFile, Model model) throws Exception {

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
		if (file == null) {
			System.out.println("nullです");
		}
		// ファイルが空でない場合に、ファイルの中身をチェックする
		if (!file.getMultipartFile().isEmpty()) {
			if (fileUploadService.fileValid(file)) {
				file.setCreateAt(dateTime);
				imageName = fileUploadService.fileUpload(file, s3Path, null);
			} else {
				model.addAttribute("lists", PostRecordCategory.values());
				model.addAttribute("message", "ファイル形式が不正です");
				return "postEdit";
			}
		}
		postform.setUserName(details.getUsername());
		postform.setCreateAt(dateTime);
		postform.setImageName(imageName);
		postRecordService.insertDiaryRecord(postform);

		return "calendar";
	}

	@GetMapping("index/record/{diaryDay}/{id}")
	public String showUserEditContent(@AuthenticationPrincipal User details, @PathVariable("id") int id,
			@PathVariable("diaryDay") String diaryDay, @ModelAttribute("fileUploadForm") FileUploadForm file,
			Model model) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = format.parse(diaryDay);

		PostForm form = postRecordService.findOneDiaryRecord(details.getUsername(), id, parsedDate);
		if (form == null) {
			return "error/404";
		}

		if (form.getImageName() != null) {
			String src = fileUploadService.fileDownload(s3Path, form.getImageName());
			model.addAttribute("exist", true);
			model.addAttribute("image", "data:image/jpg;base64," + src);
		} else {
			model.addAttribute("exist", false);
		}

		model.addAttribute("postform", form);
		model.addAttribute("lists", PostRecordCategory.values());
		return "post";
	}

	@RequestMapping(value = "/index/record/commit", method = RequestMethod.POST, params = "update")
	public String updateContent(@AuthenticationPrincipal User details,
			@ModelAttribute("postform") @Validated PostForm form, BindingResult resultForm,
			@ModelAttribute("fileUploadForm") @Validated FileUploadForm file, BindingResult resultFile, Model model)
			throws Exception {
		if (resultForm.hasErrors() || resultFile.hasErrors()) {
			model.addAttribute("lists", PostRecordCategory.values());
			return "post";
		}

//		PostForm exist = postRecordService.findOneDiaryRecord(details.getUsername(), form.getCategoryId(),
//				form.getDiaryDay());
//		if (exist != null && !exist.getCreateAt().equals(form.getCreateAt())) {
//			model.addAttribute("lists", PostRecordCategory.values());
//			model.addAttribute("message", "既に同じカテゴリ、同じ日付で登録されています");
//			return "post";
//		}

		String imageName = form.getImageName();
		LocalDateTime dateTime = form.getCreateAt();
		// ファイルが空でない場合に、ファイルの中身をチェックする
		if (!file.getMultipartFile().isEmpty()) {
			if (fileUploadService.fileValid(file)) {
				file.setCreateAt(dateTime);
				imageName = fileUploadService.fileUpload(file, s3Path, imageName);
			} else {
				model.addAttribute("lists", PostRecordCategory.values());
				model.addAttribute("message", "ファイル形式が不正です");
				return "post";
			}
		}

		form.setUserName(details.getUsername());
		form.setImageName(imageName);
		form.setCreateAt(form.getCreateAt());
		postRecordService.updateDiaryRecord(form);
		return "redirect:/calendar";
	}

	@RequestMapping(value = "/index/record/commit", method = RequestMethod.POST, params = "delete")
	public String deleteContent(@AuthenticationPrincipal User details,
			@ModelAttribute("postform") PostForm form) {
		form.setUserName(details.getUsername());
		postRecordService.deleteDiaryRecord(form);
		return "redirect:/calendar";
	}
}
