package com.vannak.qcweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vannak.qcweb.entity.LineSetting;
@Repository
public interface LineSettingRepository extends JpaRepository<LineSetting,Long>{
	@Query(value = "SELECT * FROM line_setting", nativeQuery = true)
	List<LineSetting> getLine();
}
