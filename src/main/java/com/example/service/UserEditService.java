package com.example.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.form.SignupForm;
import com.example.model.User;
import com.example.repository.UserMapper;


@Service
public class UserEditService {
	
	private final UserMapper userMapper;
	
	public UserEditService(UserMapper userMapper) {
		this.userMapper = userMapper;
		
	}
	
	
	@Transactional(readOnly = true)
	public SignupForm findUser(String userName) {
		User userinfo = userMapper.identifyUser(userName);
		if(Objects.isNull(userinfo)) {
    		return null;
    	}
		SignupForm form = new SignupForm(userinfo.getName(),userinfo.getMail(),userinfo.getPassword());
    	return form;
	}

	@Transactional(readOnly = false)
	public void deleteUser(String name) {
		userMapper.deleteUser(name);
	}
}
