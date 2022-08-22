package com.example.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.model.Post;

@Mapper
public interface PostMapper {
	
	List<Post> findAllDiaryRecords(String userName);
	Post findOneDiaryRecord(@Param("userName")String userName,
			                        @Param("categoryId")int categoryId,
			                        @Param("diaryDay")Date diaryDay);
	void insertDiaryRecord(Post record);
	void updateDiaryRecord(Post record);
	void deleteDiaryRecord(Post record);

}