package com.lp.security.test.dto.response;

import java.util.List;

import com.lp.security.test.entity.Store;

import lombok.Data;

@Data
public class UserResponse {

	private String id;
	private String name;
	private String email;
	private List<String> roles;
	private List<Store> stores;
}
