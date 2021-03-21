package com.lp.security.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp.security.test.entity.Roles;
import com.lp.security.test.repository.UserRoleRepository;


@RestController
@RequestMapping("/api")
public class Test {

	@Autowired
	UserRoleRepository roleRepository;

	@GetMapping("/test")
	public String getData() {
		return "how are u" + roleRepository.findByName(Roles.ROLE_ADMIN.name())
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));

	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('ADMIN')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

}
