package com.vannak.qcweb.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vannak.qcweb.config.jwt.LoginRequest;
import com.vannak.qcweb.config.security.AuthUser;
import com.vannak.qcweb.config.security.JwtUtils;
import com.vannak.qcweb.config.security.RoleEnum;
import com.vannak.qcweb.dto.SignupRequest;
import com.vannak.qcweb.entity.Role;
import com.vannak.qcweb.entity.User;
import com.vannak.qcweb.exception.ApiException;
import com.vannak.qcweb.repository.RoleRepository;
import com.vannak.qcweb.repository.UserRepository;
import com.vannak.qcweb.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtUtils jwtUtils;

	@Override
	public String createUser(SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Username is already taken!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already taken!");
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				passwordEncoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(RoleEnum.USER.name())
					.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Role is not found!"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				Role adminRole = roleRepository.findByName(role)
						.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, role + " Role is not found!"));
				roles.add(adminRole);
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		return jwtUtils.generateJwtToken(signUpRequest.getUsername());
	}

	@Override
	public String authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		    SecurityContextHolder.getContext().setAuthentication(authentication);
		    AuthUser userPrincipal = (AuthUser) authentication.getPrincipal();

		    return jwtUtils.generateJwtToken(userPrincipal.getUsername());
	}

	@Override
	public List<User> GetUser() {
		// TODO Auto-generated method stub
		return userRepository.GetUser();
		
	}

}
