package com.example.service;

import java.util.Date;
import java.util.Objects;

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
	
	
	
	 @Transactional(readOnly = true)
	    public PostForm findOneDiaryRecord(String userName,int categoryId,Date diaryDay) {
	    	Post diary = PostMapper.findOneDiaryRecord(userName, categoryId, diaryDay);
	    	if(Objects.isNull(diary)) {
	    		return null;
	    	}
	    	PostForm form = new PostForm(diary.getUserName(),diary.getCategoryId(),diary.getDiaryDay(),
	    			                                   diary.getRecord1(),diary.getRecord2(),diary.getRecord3(),
	    			                                   diary.getImageName(),diary.getMemo(),diary.getCreateAt());
	    	return form;
	    	
	    }
}
