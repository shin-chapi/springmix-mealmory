package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserMapper;

@Service
public class UserRegisterationService {
	
	@Autowired
	public UserMapper userMapper;
	
//	@Autowired
	public BCryptPasswordEncoder encoder;
	
	@Transactional
	public  void registerUser(User user) {
		
		
		
		
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
		userMapper.registerUser(user);
		
	}
	
	

}
