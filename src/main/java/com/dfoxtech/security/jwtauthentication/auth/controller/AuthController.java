package com.dfoxtech.security.jwtauthentication.auth.controller;

import com.dfoxtech.security.jwtauthentication.auth.dto.AuthReponseDTO;
import com.dfoxtech.security.jwtauthentication.auth.dto.AuthRequestDto;
import com.dfoxtech.security.jwtauthentication.auth.dto.AuthResponseLoggedDTO;
import com.dfoxtech.security.jwtauthentication.auth.dto.RegisterDtoRequest;
import com.dfoxtech.security.jwtauthentication.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth/")
public class AuthController {
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDtoRequest request) {
        authService.registerUser(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequestDto) {
        AuthResponseLoggedDTO reponseDTO=authService.login(authRequestDto);
        return ResponseEntity.ok().body(reponseDTO);
    }
    @GetMapping("me")
    public String me(Authentication authentication){
        return authentication.getName();
    }
}
