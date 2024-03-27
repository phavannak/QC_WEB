package com.vannak.qcweb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vannak.qcweb.dto.ProductDTO;
import com.vannak.qcweb.dto.ProductImportDTO;
import com.vannak.qcweb.entity.Product;
import com.vannak.qcweb.entity.ProductImportHistory;
import com.vannak.qcweb.service.ColorService;
import com.vannak.qcweb.service.ModelService;

@Mapper(componentModel = "spring", 
	uses = {ModelService.class, ColorService.class})
public interface ProductMapper {

	@Mapping(target = "model", source = "modelId")
	@Mapping(target = "color", source = "colorId")
	Product toProduct(ProductDTO productDTO);
	
	@Mapping(target = "dateImport", source = "importDTO.importDate")
	@Mapping(target = "pricePerUnit", source = "importDTO.importPrice")
	@Mapping(target = "product", source = "product")
	@Mapping(target = "id", ignore = true)
	ProductImportHistory toProductImportHistory(ProductImportDTO importDTO, Product product);
	
	
}
