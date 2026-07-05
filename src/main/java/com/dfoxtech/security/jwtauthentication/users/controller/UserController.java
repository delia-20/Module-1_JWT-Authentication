package com.dfoxtech.security.jwtauthentication.users.controller;

import com.dfoxtech.security.jwtauthentication.users.dto.UserCreateDto;
import com.dfoxtech.security.jwtauthentication.users.entity.User;
import com.dfoxtech.security.jwtauthentication.users.repository.UserRepository;
import com.dfoxtech.security.jwtauthentication.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("api/public/users")
    public ResponseEntity<?> getAllUsers(Authentication authentication){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }


    //dev environment

    @PostMapping("users/admin")
    public ResponseEntity<?> createUser(@RequestBody UserCreateDto user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }
    @GetMapping("users/admin")
    public ResponseEntity<?> getUsers() {
        ArrayList<User> users=userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }
    @DeleteMapping("users/admin/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable ("id") UUID id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();

    }
}
