package com.vannak.qcweb.service;

import java.util.List;

import com.vannak.qcweb.config.jwt.LoginRequest;
import com.vannak.qcweb.dto.SignupRequest;
import com.vannak.qcweb.entity.User;

public interface AuthService {
	String createUser(SignupRequest signUpRequest);
	String authenticateUser(LoginRequest loginRequest);
	List<User> GetUser();
}
