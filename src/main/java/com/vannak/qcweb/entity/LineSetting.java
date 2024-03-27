package com.vannak.qcweb.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="line_setting")
public class LineSetting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
