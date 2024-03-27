package com.vannak.qcweb.service;

import com.vannak.qcweb.entity.Color;

public interface ColorService {
	Color create(Color color);
	Color getById(Long id);
}
