package com.dfoxtech.security.jwtauthentication.security.service;

import com.dfoxtech.security.jwtauthentication.auth.exceptions.InvalidCredentialException;
import com.dfoxtech.security.jwtauthentication.auth.repository.AuthRepository;
import com.dfoxtech.security.jwtauthentication.users.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceDetails implements UserDetailsService {

    private final AuthRepository authRepository;
    public UserServiceDetails(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=authRepository.findByUsername(username)
                .orElseThrow(() -> new  InvalidCredentialException("user no found"));
        return  new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(
                        new SimpleGrantedAuthority("ROLE_USER")
                )
        );


    }
}
