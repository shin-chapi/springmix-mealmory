package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.form.PostForm;
import com.example.model.Post;
import com.example.repository.PostMapper;


@Service
public class PostRecordService {

	private final PostMapper PostMapper;

	public PostRecordService(PostMapper PostMapper) {
		this.PostMapper = PostMapper;
	}

	@Transactional(readOnly = false)
	public void insertDiaryRecord(PostForm form) {
		Post diary = new Post(form.getUserName(), form.getCategoryId(), form.getDiaryDay(), form.getRecord1(),
				form.getRecord2(), form.getRecord3(), form.getImageName(), form.getMemo(), form.getCreateAt(),
				form.getCreateAt());
		PostMapper.insertDiaryRecord(diary);
	}
}
