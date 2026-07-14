package com.pongs.user_service.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pongs.user_service.model.entity.User;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    void insert(User user);
}
