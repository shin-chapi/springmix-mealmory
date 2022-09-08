package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.UserMapper;


@Service
public class UserEditService {
	
	private final UserMapper userMapper;
	
	public UserEditService(UserMapper userMapper) {
		this.userMapper = userMapper;
		
	}

	@Transactional(readOnly = false)
	public void deleteUser(String name) {
		userMapper.deleteUser(name);
	}
}
