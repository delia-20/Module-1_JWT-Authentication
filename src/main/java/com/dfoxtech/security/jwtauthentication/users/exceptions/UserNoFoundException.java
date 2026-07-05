package com.dfoxtech.security.jwtauthentication.users.exceptions;

public class UserNoFoundException extends RuntimeException {
    public UserNoFoundException(String message) {
        super(message);
    }
}
