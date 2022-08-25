package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Post;
import com.example.repository.PostMapper;


@Service
public class PostRecordService {

	private final PostMapper PostMapper;

	public PostRecordService(PostMapper PostMapper) {
		this.PostMapper = PostMapper;
	}

	@Transactional(readOnly = false)
	public void insertDiaryRecord(Post entity) {
//		Post diary = new Post(form.getUserName(), form.getCategoryId(), form.getDiaryDay(), form.getRecord1(),
//				form.getRecord2(), form.getRecord3(),  form.getMemo(),form.getImageName(), form.getCreateAt(),
//				form.getCreateAt());
		PostMapper.insertDiaryRecord(entity);
	}
}
