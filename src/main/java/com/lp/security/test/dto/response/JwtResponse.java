package com.lp.security.test.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

	
	
	private String id;
	private String name;
	private String email;
	private List<String> roles;
	private String tokenType ="Bearer";
	private String token;
	public JwtResponse(String token, String id, String name, String email, List<String> roles) {
		super();
		this.token = token;
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}
}
