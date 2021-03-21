package com.lp.security.test.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lp.security.test.entity.UserRole;


public interface UserRoleRepository extends MongoRepository<UserRole, String> {
	
	public Optional<UserRole> findByName(String name);

}
