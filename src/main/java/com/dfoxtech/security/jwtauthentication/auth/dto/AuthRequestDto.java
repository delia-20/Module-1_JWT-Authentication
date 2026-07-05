package com.dfoxtech.security.jwtauthentication.auth.dto;

public record AuthRequestDto(
        String username,
        String password
) {
}
