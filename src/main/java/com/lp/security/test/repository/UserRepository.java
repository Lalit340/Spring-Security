package com.lp.security.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lp.security.test.entity.User;



@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	public Optional<User> findByEmail(String email);

	public boolean existsByEmail(String email);
	
//	@Query(value = "Select us from user us where us.role.name != 'ROLE_ADMIN' And us.role.name = ?1" )
	public List<User> findByRole_Name(String name);
	
	

}
