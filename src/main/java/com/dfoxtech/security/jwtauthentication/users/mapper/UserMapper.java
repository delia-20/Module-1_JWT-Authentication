package com.dfoxtech.security.jwtauthentication.users.mapper;

import com.dfoxtech.security.jwtauthentication.users.dto.UserCreateDto;
import com.dfoxtech.security.jwtauthentication.users.dto.UserReponseDTO;
import com.dfoxtech.security.jwtauthentication.users.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User requestToUser(UserCreateDto request) {
        User user = new User(
                request.username(),
                request.password(),
                request.email(),
                request.firstname(),
                request.lastname(),
                request.dateOfBirth()
        );
        return user;

    }

    public UserReponseDTO responseToUserCreateDto(User user) {
        return new UserReponseDTO(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth()
        );
    }
}
