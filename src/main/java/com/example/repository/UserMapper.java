package com.example.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.User;

@Mapper
public interface UserMapper {
 
	public User identifyUser(String name);
	
	public void registerUser(User user);
}
