package com.vannak.qcweb.service;

import java.util.List;

import com.vannak.qcweb.entity.Model;

public interface ModelService {
	Model save(Model model);
	List<Model> getByBrand(Long brandId);
	Model getById(Long id);
}
