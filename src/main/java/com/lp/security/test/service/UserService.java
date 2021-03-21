package com.lp.security.test.service;

import java.util.List;

import com.lp.security.test.dto.response.UserResponse;


public interface UserService {
	
	public List<UserResponse> getAllUsers();

}
