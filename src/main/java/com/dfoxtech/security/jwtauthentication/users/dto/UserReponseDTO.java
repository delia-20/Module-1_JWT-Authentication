package com.dfoxtech.security.jwtauthentication.users.dto;

import java.util.Date;
import java.util.UUID;

public record UserReponseDTO(
        UUID userID,
        String username,
        String password,
        String email,
        String firstname,
        String lastname,
        Date dateOfBirth

) {
}
