package com.vannak.qcweb.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="new_order_log")
public class NewOrderLog {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Long new_order_id;
private String factory;
private String lineno;
private String mo;
private String cpo;
private String article;
private int orders;
private LocalDate create_date;
private String create_by;
private LocalDate update_date;
private String update_by;
private String status;
private String delete_date;
private LocalDate delete_by;

}
