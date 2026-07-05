package com.dfoxtech.security.jwtauthentication.users.controller;

import com.dfoxtech.security.jwtauthentication.auth.exceptions.InvalidCredentialException;
import com.dfoxtech.security.jwtauthentication.users.exceptions.UserNoFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController  {


    @ExceptionHandler(UserNoFoundException.class)
    public ResponseEntity<?> handleUserNoFoundException(UserNoFoundException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

}
