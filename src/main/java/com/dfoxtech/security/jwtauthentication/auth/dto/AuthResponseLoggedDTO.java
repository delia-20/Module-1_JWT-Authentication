package com.dfoxtech.security.jwtauthentication.auth.dto;

import lombok.Data;

@Data
public class AuthResponseLoggedDTO {
    private String WelcomeMessage;
    private String token;
    private AuthReponseDTO authReponseDTO;
    public AuthResponseLoggedDTO(AuthReponseDTO authReponseDTO) {}

    public AuthResponseLoggedDTO(String welcomeMessage,String token, AuthReponseDTO authReponseDTO) {
        this.WelcomeMessage = welcomeMessage;
        this.token = token;
        this.authReponseDTO = authReponseDTO;
    }

}
