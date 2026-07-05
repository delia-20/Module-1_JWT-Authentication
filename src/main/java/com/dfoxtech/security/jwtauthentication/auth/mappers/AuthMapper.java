package com.dfoxtech.security.jwtauthentication.auth.mappers;

import com.dfoxtech.security.jwtauthentication.auth.dto.AuthReponseDTO;
import com.dfoxtech.security.jwtauthentication.auth.dto.RegisterDtoRequest;
import com.dfoxtech.security.jwtauthentication.users.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public User requestToEntity(RegisterDtoRequest request) {
        User user=new User(request.username(),request.password(),request.email(),request.firstname(),request.lastname(),request.dateOfBirth());
        return user;
    }
    public AuthReponseDTO toResponse(User user) {
        AuthReponseDTO reponse= new AuthReponseDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth()
        );
        return reponse;
    }
}
