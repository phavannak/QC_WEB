package com.vannak.qcweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vannak.qcweb.entity.LineSetting;
import com.vannak.qcweb.service.LinesSettingService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/linesetting")
public class LineSettingController {
	private final LinesSettingService linesetting;
	@GetMapping("/list")
	public ResponseEntity<?> getLineNo(){
		 List<LineSetting> lineno=linesetting.getFactory();
		 return ResponseEntity.ok(lineno);
	}
	

}
