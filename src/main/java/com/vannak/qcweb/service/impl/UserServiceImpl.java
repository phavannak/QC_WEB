package com.vannak.qcweb.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.vannak.qcweb.config.security.AuthUser;
import com.vannak.qcweb.config.security.UserService;
import com.vannak.qcweb.entity.Role;
import com.vannak.qcweb.entity.User;
import com.vannak.qcweb.exception.ApiException;
import com.vannak.qcweb.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;

	@Override
	public Optional<AuthUser> findUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found "));
		
		AuthUser authUser = AuthUser.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.authorities(getAuthorities(user.getRoles()))
				.accountNonExpired(user.isAccountNonExpired())
				.accountNonLocked(user.isAccountNonLocked())
				.credentialsNonExpired(user.isCredentialsNonExpired())
				.enabled(user.isEnabled())
				.build();
		return Optional.ofNullable(authUser);
	}
	
	private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles){
				Set<SimpleGrantedAuthority> authorities1 = roles.stream()
					.map(role -> new SimpleGrantedAuthority("ROLE_"+ role.getName()))
					.collect(Collectors.toSet());
				
				Set<SimpleGrantedAuthority> authorities = roles.stream()
						.flatMap(role ->toStream(role))
						.collect(Collectors.toSet());
				authorities.addAll(authorities1);
				return authorities;
	}
	
	private Stream<SimpleGrantedAuthority> toStream(Role role){
		return role.getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getName()));
	}
	
	
	

}
