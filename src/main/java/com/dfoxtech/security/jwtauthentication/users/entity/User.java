package com.dfoxtech.security.jwtauthentication.users.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    UUID userId;
    String username;
    String password;
    String email;
    String firstName;
    String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date dateOfBirth;


    public User() {}
    public User(String username, String password, String email, String firstName, String lastName, Date dateOfBirth) {
        //this.userId = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
}
