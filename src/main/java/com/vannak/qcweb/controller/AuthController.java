package com.vannak.qcweb.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vannak.qcweb.config.jwt.LoginRequest;
import com.vannak.qcweb.dto.SignupRequest;
import com.vannak.qcweb.entity.User;
import com.vannak.qcweb.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		String jwt = authService.authenticateUser(loginRequest);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", "Bearer " + jwt);
		responseHeaders.set("Access-Control-Expose-Headers", "Authorization");
		
		return ResponseEntity.ok().headers(responseHeaders).build();
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		String jwt = authService.createUser(signUpRequest);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", "Bearer " + jwt);
		responseHeaders.set("role", "vannak");
		
		return ResponseEntity.ok().headers(responseHeaders).build();
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getUser(){
		List<User> user=authService.GetUser();
		return ResponseEntity.ok(user);
	}
	
	

}
