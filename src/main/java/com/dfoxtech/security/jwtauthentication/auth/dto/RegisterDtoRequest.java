package com.dfoxtech.security.jwtauthentication.auth.dto;

import java.util.Date;

public record RegisterDtoRequest(
        String username,
        String password,
        String passwordConfirm,
        String email,
        String firstname,
        String lastname,
        Date dateOfBirth){
}
