package com.example.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Post() {

	}

	public Post(String name, int categoryId, Date diaryDay, String record1, String record2, String record3,
			String imageName, String memo, LocalDateTime createAt, LocalDateTime updateAt) {
		
		
		this.categoryId = categoryId;
		this.diaryDay = diaryDay;
		this.record1 = record1;
		this.record2 = record2;
		this.record3 = record3;
		this.imageName = imageName;
		this.memo = memo;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}
}
