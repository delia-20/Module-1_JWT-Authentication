package com.dfoxtech.security.jwtauthentication.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("home")
    public ResponseEntity<?> home(){
        return ResponseEntity.ok().body("Home");
    }
}
