//package com.example.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.model.User;
//import com.example.repository.UserRepository;
//
//
//@Service
//public class UserService {
//
//	 /**
//	   * ユーザー情報 Repository
//	   */
//	  @Autowired
//	  private UserRepository userRepository;
//	  /**
//	   * ユーザー情報 全検索
//	   * @return 検索結果
//	   */
//	  public List<User> searchAll() {
//	    return userRepository.findAll();
//	  }
//}
