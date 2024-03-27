package com.vannak.qcweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vannak.qcweb.dto.SaleDTO;
import com.vannak.qcweb.dto.SaleResponeDTO;
import com.vannak.qcweb.service.SaleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("sales")
public class SaleController {

	private final SaleService saleService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody SaleDTO saleDTO) {
		SaleResponeDTO saleResponeDTO = saleService.sell(saleDTO);
		return ResponseEntity.ok(saleResponeDTO);
	}
	
	@PutMapping("{saleId}/cancel")
	public ResponseEntity<?> cancelSale(@PathVariable Long saleId){
		saleService.cancelSale(saleId);
		return ResponseEntity.ok().build();
	}
	

}
