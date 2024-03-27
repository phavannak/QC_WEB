package com.vannak.qcweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vannak.qcweb.entity.Model;

public interface ModelRepository extends JpaRepository<Model, Long>{
	List<Model> findByBrandId(Long brandId);
	
}
