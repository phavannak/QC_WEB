package com.vannak.qcweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vannak.qcweb.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	
	@Query(value = "SELECT id,first_name,last_name,username,password,email,account_non_expired,account_non_locked,credentials_non_expired,enabled\n"
			+ " FROM Users where id='1'",nativeQuery = true)
	List<User> GetUser();
	
}
