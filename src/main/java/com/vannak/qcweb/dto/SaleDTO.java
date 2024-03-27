package com.vannak.qcweb.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class SaleDTO {
	@NotEmpty
	private List<ProductSoldDTO> products;
	private LocalDate saleDate;
}
