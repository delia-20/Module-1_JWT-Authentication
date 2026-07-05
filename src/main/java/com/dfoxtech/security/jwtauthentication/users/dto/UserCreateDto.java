package com.dfoxtech.security.jwtauthentication.users.dto;

import java.util.Date;

public record UserCreateDto(String username,
                            String password,
                            String email,
                            String firstname,
                            String lastname,
                            Date dateOfBirth) {
}
