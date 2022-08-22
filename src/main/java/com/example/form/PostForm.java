package com.example.form;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PostForm {

	private String userName;

	@Min(value=1,message="カテゴリを選んでください")
	private int categoryId;
	
	@NotNull(message="日付を入力してください")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date diaryDay;
	
	@NotBlank
	@Size(max=50,message="50文字以内で入力してください")
	private String record1;
	
	@NotBlank
	@Size(max=50,message="50文字以内で入力してください")
	private String record2;
	
	@NotBlank
	@Size(max=50,message="50文字以内で入力してください")
	private String record3;
	
	private String imageName;
	
	@NotBlank
	@Size(max=100,message="100文字以内で入力してください")
	private String memo;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createAt;
	
	
public PostForm() {
		
	}

	public PostForm(String userName,int categoryId,Date diaryDay, 
			               String record1, String record2, String record3,
			               String imageName,String memo, LocalDateTime createAt) {
		this.userName = userName;
		this.categoryId = categoryId;
		this.diaryDay = diaryDay;
		this.record1 = record1;
		this.record2 = record2;
		this.record3 = record3;
		this.imageName = imageName;
		this.memo = memo;
		this.createAt = createAt;
	}

}
