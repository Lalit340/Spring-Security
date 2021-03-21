package com.lp.security.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp.security.test.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping(value = "/edit")
	public ResponseEntity<?> getSubUser( ){
		
		service.getAllUsers();
		return null;
		
	}
}
