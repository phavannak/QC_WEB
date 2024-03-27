package com.vannak.qcweb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.vannak.qcweb.dto.NewOrderDTO;
import com.vannak.qcweb.entity.NewOrder;

@Mapper
public interface NewOrderMapper {
	NewOrderMapper Instance=Mappers.getMapper(NewOrderMapper.class);
	NewOrder toNewOrder(NewOrderDTO dto);
	NewOrderDTO toNewOrderDTO(NewOrder entity);
}
