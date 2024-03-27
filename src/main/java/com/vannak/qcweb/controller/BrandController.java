package com.vannak.qcweb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vannak.qcweb.dto.BrandDTO;
import com.vannak.qcweb.dto.ModelDTO;
import com.vannak.qcweb.dto.PageDTO;
import com.vannak.qcweb.entity.Brand;
import com.vannak.qcweb.entity.Model;
import com.vannak.qcweb.mapper.BrandMapper;
import com.vannak.qcweb.mapper.ModelEntityMapper;
import com.vannak.qcweb.service.BrandService;
import com.vannak.qcweb.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("brands")
public class BrandController {
	
	private final BrandService brandService;
	private final ModelService modelService;
	private final ModelEntityMapper modelMapper;
	
	@PreAuthorize("hasAuthority('brand:write')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.create(brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Long brandId){
		Brand brand = brandService.getById(brandId);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}
	
	@PreAuthorize("hasAuthority('brand:write')")
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long brandId, @RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		Brand updatedBrand = brandService.update(brandId, brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updatedBrand));
	}
	
//	@PreAuthorize("hasAuthority('brand:read')")
	@GetMapping
	public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
		Page<Brand> page = brandService.getBrands(params);
		PageDTO pageDTO = new PageDTO(page);
		/*
		List<BrandDTO> list = brandService.getBrands(params)
			.stream()
			.map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
			.collect(Collectors.toList());
		*/
		return ResponseEntity.ok(pageDTO);	
	}
	
	@GetMapping("{id}/models")
	public ResponseEntity<?> getModelsByBrand(@PathVariable("id") Long brandId){
		List<Model> brands = modelService.getByBrand(brandId);
		List<ModelDTO> list = brands.stream()
			.map(modelMapper::toModelDTO)
			.toList();
		return ResponseEntity.ok(list);
	}
	
	
}
