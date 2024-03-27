package com.vannak.qcweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vannak.qcweb.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(String username);
	
}
