package com.vannak.qcweb.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vannak.qcweb.entity.Model;
import com.vannak.qcweb.exception.ResourceNotFoundException;
import com.vannak.qcweb.repository.ModelRepository;
import com.vannak.qcweb.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {
	private final ModelRepository modelRepository;

	@Override
	public Model save(Model model) {
		return modelRepository.save(model);
	}

	@Override
	public List<Model> getByBrand(Long brandId) {
		return modelRepository.findByBrandId(brandId);
	}

	@Override
	public Model getById(Long id) {
		return modelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Model", id));
	}

}
