package com.lp.security.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.security.test.dto.response.UserResponse;
import com.lp.security.test.entity.Roles;
import com.lp.security.test.entity.User;
import com.lp.security.test.repository.UserRepository;
import com.lp.security.test.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<UserResponse> getAllUsers() {
		 List<User> usersList = userRepository.findByRole_Name(Roles.ROLE_USER.name());
		 usersList.forEach(use -> System.out.println(use));
		return null;
	}

}
