package com.vannak.qcweb.service.impl;

import org.springframework.stereotype.Service;

import com.vannak.qcweb.entity.Color;
import com.vannak.qcweb.exception.ResourceNotFoundException;
import com.vannak.qcweb.repository.ColorRepository;
import com.vannak.qcweb.service.ColorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService{

	private final ColorRepository colorRepository;
	@Override
	public Color create(Color color) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getById(Long id) {
		return colorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Color", id));
	}

}
