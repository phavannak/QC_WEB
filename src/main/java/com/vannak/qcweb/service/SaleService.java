package com.vannak.qcweb.service;

import com.vannak.qcweb.dto.SaleDTO;
import com.vannak.qcweb.dto.SaleResponeDTO;
import com.vannak.qcweb.entity.Sale;

public interface SaleService {
	SaleResponeDTO sell(SaleDTO saleDTO);
	Sale getById(Long saleId);
	void cancelSale(Long saleId);
}
