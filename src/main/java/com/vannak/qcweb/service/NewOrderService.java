package com.vannak.qcweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.vannak.qcweb.entity.NewOrder;

public interface NewOrderService {
	NewOrder createNewOrder(NewOrder neworder);
//	Page<Brand> getBrands(Map<String, String> params);
	Page<NewOrder> ListNewOrder(Map<String, String> params);
	NewOrder FindById(Long id);
	List<NewOrder> Listquery();
	NewOrder UpdateNewOrder(Long id, NewOrder neworder);
	void DeleteById(Long id);
}
