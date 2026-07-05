package com.dfoxtech.security.jwtauthentication.auth.dto;

import java.util.Date;
import java.util.UUID;

public record AuthReponseDTO(
        UUID userID,
        String username,
        String email,
        String firstname,
        String lastname,
        Date dateOfBirth
) {
}
