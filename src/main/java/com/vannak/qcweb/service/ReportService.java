package com.vannak.qcweb.service;

import java.time.LocalDate;
import java.util.List;

import com.vannak.qcweb.dto.ProductReportDTO;
import com.vannak.qcweb.dto.report.ExpenseReportDTO;
import com.vannak.qcweb.projection.ProductSold;

public interface ReportService {
	
	List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
	List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate);
	
	List<ExpenseReportDTO> getExpenseReport(LocalDate startDate, LocalDate endDate);
	
}
