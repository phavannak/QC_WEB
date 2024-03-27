package com.vannak.qcweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vannak.qcweb.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long>
{
	
}
