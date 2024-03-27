package com.vannak.qcweb.config.security;

import java.util.Optional;

public interface UserService {
	Optional<AuthUser> findUserByUsername(String username);
}
