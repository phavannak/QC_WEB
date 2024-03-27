package com.vannak.qcweb.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vannak.qcweb.dto.ProductSoldDTO;
import com.vannak.qcweb.dto.SaleDTO;
import com.vannak.qcweb.dto.SaleResponeDTO;
import com.vannak.qcweb.entity.Product;
import com.vannak.qcweb.entity.Sale;
import com.vannak.qcweb.entity.SaleDetail;
import com.vannak.qcweb.exception.ApiException;
import com.vannak.qcweb.exception.ResourceNotFoundException;
import com.vannak.qcweb.repository.ProductRepository;
import com.vannak.qcweb.repository.SaleDetailRepository;
import com.vannak.qcweb.repository.SaleRepository;
import com.vannak.qcweb.service.ProductService;
import com.vannak.qcweb.service.SaleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService{
	private final ProductService productService;
	private final ProductRepository productRepository;
	private final SaleRepository saleRepository;
	private final SaleDetailRepository saleDetailRepository;

	@Override
	public SaleResponeDTO sell(SaleDTO saleDTO) {
		
		List<Long> productIds = saleDTO.getProducts().stream()
				.map(ProductSoldDTO::getProductId)
				.toList();
			// validate product
		productIds.forEach(productService::getById);
		
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		
		// validate stock
		saleDTO.getProducts()
			.forEach(ps ->{
				Product product = productMap.get(ps.getProductId());
				if(product.getAvailableUnit() < ps.getNumberOfUnit()) {
					throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock".formatted(product.getName()));
				}
			});
		
		// Sale
		Sale sale = new Sale();
		sale.setSoldDate(saleDTO.getSaleDate());
		saleRepository.save(sale);
		
		// Sale Detail
		saleDTO.getProducts().forEach(ps ->{
			Product product = productMap.get(ps.getProductId());
			SaleDetail saleDetail = new SaleDetail();
			saleDetail.setAmount(product.getSalePrice());
			saleDetail.setProduct(product);
			saleDetail.setSale(sale);
			saleDetail.setUnit(ps.getNumberOfUnit());
			saleDetailRepository.save(saleDetail);
			
			// cut stock
			Integer availableUnit =  product.getAvailableUnit() - ps.getNumberOfUnit();
			product.setAvailableUnit(availableUnit);
			productRepository.save(product);
		});
		
		SaleResponeDTO responeDTO = new SaleResponeDTO();
		responeDTO.setSaleId(sale.getId());
		return responeDTO;
	}
	
	
	
	
	
	private void saveSale(SaleDTO saleDTO) {
		Sale sale = new Sale();
		sale.setSoldDate(saleDTO.getSaleDate());
		saleRepository.save(sale);
		
		
		//Sale Detail
		saleDTO.getProducts().forEach(ps ->{
			SaleDetail saleDetail = new SaleDetail();
			saleDetail.setAmount(null);
		});
	}
	
	private void validate(SaleDTO saleDTO) {
		saleDTO.getProducts().forEach(ps ->{
			Product product = productService.getById(ps.getProductId());
			if(product.getAvailableUnit() < ps.getNumberOfUnit()) {
				throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock".formatted(product.getName()));
			}
		});
	}
	
	
	
	private void validate2(SaleDTO saleDTO) {
		
		
		List<Long> productIds = saleDTO.getProducts().stream()
			.map(ProductSoldDTO::getProductId)
			.toList();
		// validate product
		productIds.forEach(productService::getById);
		
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		
		// validate stock
		saleDTO.getProducts()
			.forEach(ps ->{
				Product product = productMap.get(ps.getProductId());
				if(product.getAvailableUnit() < ps.getNumberOfUnit()) {
					throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock".formatted(product.getName()));
				}
			});
		
	}





	@Override
	public void cancelSale(Long saleId) {
		// update sale status
		Sale sale = getById(saleId);
		sale.setActive(false);
		saleRepository.save(sale);
		
		// update stock
		List<SaleDetail> saleDetails = saleDetailRepository.findBySaleId(saleId);
		
		List<Long> productIds = saleDetails.stream()
			.map(sd -> sd.getProduct().getId())
			.toList();
		
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		saleDetails.forEach(sd ->{
			 Product product = productMap.get(sd.getProduct().getId());
			 product.setAvailableUnit(product.getAvailableUnit() + sd.getUnit());
			 productRepository.save(product);
		});
		
		
	}





	@Override
	public Sale getById(Long saleId) {
		return saleRepository.findById(saleId)
					.orElseThrow(() -> new ResourceNotFoundException("Sale", saleId));
	}

}
