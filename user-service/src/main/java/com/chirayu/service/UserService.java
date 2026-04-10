package com.chirayu.service;

import com.chirayu.dto.CreateUserRequest;
import com.chirayu.dto.UserDto;
import com.chirayu.dto.UserUpdateRequest;


public interface UserService {
    UserDto save(CreateUserRequest userDto);
    UserDto findById(String userID);
    UserDto update(String userId, UserUpdateRequest user);
    void delete(String userId);
}
