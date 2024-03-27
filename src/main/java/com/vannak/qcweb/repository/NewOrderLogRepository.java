package com.vannak.qcweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vannak.qcweb.entity.NewOrder;
import com.vannak.qcweb.entity.NewOrderLog;
@Repository
public interface NewOrderLogRepository extends JpaRepository<NewOrderLog, Long>{

}
