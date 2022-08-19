package com.example.form;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class PostForm {

	private Long id;

	private int categoryId;

	private Date diaryDay;

	private String record1;

	private String record2;

	private String record3;

	private String imageName;

	private String memo;

	private LocalDateTime createAt;

	private LocalDateTime updateAt;

}
