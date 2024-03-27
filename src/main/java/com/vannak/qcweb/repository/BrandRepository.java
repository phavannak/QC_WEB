package com.vannak.qcweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vannak.qcweb.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand>
{
	List<Brand> findByNameLike(String name);
	List<Brand> findByNameContaining(String name);
	
	
	
}
