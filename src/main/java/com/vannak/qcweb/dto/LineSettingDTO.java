package com.vannak.qcweb.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class LineSettingDTO {
	private Long id;
	private String factory;
	private String building;
	private String floor;
	private String lineno;
	private String status;
	private LocalDate create_date;
	private String create_by;
	private LocalDate update_date;
	private String update_by;
}
