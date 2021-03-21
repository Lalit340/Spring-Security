package com.lp.security.test.dto;

import java.util.Set;

import lombok.Data;

@Data
public class UserEditRequest {

	private String name;
	private String email;
	private Set<String> roles;
	
	
}
