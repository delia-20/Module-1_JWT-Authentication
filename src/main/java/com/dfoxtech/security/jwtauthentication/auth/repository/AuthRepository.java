package com.dfoxtech.security.jwtauthentication.auth.repository;

import com.dfoxtech.security.jwtauthentication.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.server.UID;
import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
