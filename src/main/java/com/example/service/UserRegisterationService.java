package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
	public void registerUser(User user) {
		
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		encoder  = context.getBean(BCryptPasswordEncoder.class);
		
		
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
		userMapper.registerUser(user);
		context.close();
	}
	
	

}
