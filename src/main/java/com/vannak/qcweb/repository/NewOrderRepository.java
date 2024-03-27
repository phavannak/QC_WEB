package com.vannak.qcweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vannak.qcweb.entity.NewOrder;

@Repository
public interface NewOrderRepository extends JpaRepository<NewOrder, Long>{
	@Query("select order.id,order.status from NewOrder order")
	List<NewOrder> ListOrder();
}


