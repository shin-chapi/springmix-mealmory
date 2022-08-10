package com.example.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.model.User;

@Mapper
public interface UserListMapper {
    public User certificate(@Param("id") String id);
}