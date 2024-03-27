package com.vannak.qcweb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vannak.qcweb.dto.NewOrderDTO;
import com.vannak.qcweb.dto.PageDTO;
import com.vannak.qcweb.entity.Brand;
import com.vannak.qcweb.entity.NewOrder;
import com.vannak.qcweb.exception.ResourceNotFoundException;
import com.vannak.qcweb.mapper.BrandMapper;
import com.vannak.qcweb.mapper.NewOrderMapper;
import com.vannak.qcweb.service.BrandService;
import com.vannak.qcweb.service.ModelService;
import com.vannak.qcweb.service.NewOrderService;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step.Resolution;
import org.springframework.web.bind.annotation.PutMapping;



@RequiredArgsConstructor
@RestController
@RequestMapping("neworders")
public class NewOrderController {
private final NewOrderService neworderservice;
private NewOrderMapper newordermapper;

@PostMapping("/create")
public ResponseEntity<?> create(@RequestBody NewOrderDTO dto) {
    NewOrder neworder=newordermapper.Instance.toNewOrder(dto);
    neworder=neworderservice.createNewOrder(neworder);
    return ResponseEntity.ok(newordermapper.Instance.toNewOrderDTO(neworder));
}

@GetMapping
public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
	Page<NewOrder> page = neworderservice.ListNewOrder(params);
	PageDTO pageDTO = new PageDTO(page);
	/*
	List<BrandDTO> list = brandService.getBrands(params)
		.stream()
		.map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
		.collect(Collectors.toList());
	*/
	return ResponseEntity.ok(pageDTO);	
	
}

@GetMapping("{id}")
public ResponseEntity<?> GetNeworderByID(@PathVariable("id") long neworderid){
	NewOrder neworder=neworderservice.FindById(neworderid);
	return ResponseEntity.ok(newordermapper.Instance.toNewOrderDTO(neworder));
}

@GetMapping("/List")
public ResponseEntity<?> ListOrder(){
	List<NewOrder> list =neworderservice.Listquery();
	return ResponseEntity.ok(list);
}

@PutMapping("{id}")
public ResponseEntity<?> updateneworder(@PathVariable Long id, @RequestBody NewOrderDTO neworderDTO) {
    NewOrder neworder=newordermapper.Instance.toNewOrder(neworderDTO);
	NewOrder updateneworder=neworderservice.UpdateNewOrder(id, neworder);
	return ResponseEntity.ok(newordermapper.Instance.toNewOrderDTO(updateneworder));
}

//delete

@DeleteMapping("{id}")
public ResponseEntity<?> deleteneworder(@PathVariable("id") Long id){
	String message;
	try {
		
		neworderservice.DeleteById(id);
		message="Data have been deleted";
		
	} catch (Exception e) {
	message=e.getMessage();
	}
	return new ResponseEntity<>(message,HttpStatus.OK);
}


	
	
}
