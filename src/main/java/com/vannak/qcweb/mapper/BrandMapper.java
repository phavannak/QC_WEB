package com.vannak.qcweb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.vannak.qcweb.dto.BrandDTO;
import com.vannak.qcweb.entity.Brand;

@Mapper
public interface BrandMapper {
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
	Brand toBrand(BrandDTO dto);
	BrandDTO toBrandDTO(Brand entity);
}

