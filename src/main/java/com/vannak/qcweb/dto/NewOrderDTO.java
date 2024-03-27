package com.vannak.qcweb.dto;

import java.sql.Date;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class NewOrderDTO {
	private Long id;
	private String factory;
	private String lineno;
	private String mo;
	private String cpo;
	private String article;
	private int orders;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate create_date;
	private String create_by;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate update_date;
	private String update_by;
	private String status;
}
