package com.dfoxtech.security.jwtauthentication.auth.service;

import com.dfoxtech.security.jwtauthentication.auth.dto.AuthRequestDto;
import com.dfoxtech.security.jwtauthentication.auth.dto.AuthResponseLoggedDTO;
import com.dfoxtech.security.jwtauthentication.auth.dto.RegisterDtoRequest;
import com.dfoxtech.security.jwtauthentication.auth.exceptions.InvalidCredentialException;
import com.dfoxtech.security.jwtauthentication.auth.repository.AuthRepository;
import com.dfoxtech.security.jwtauthentication.auth.mappers.AuthMapper;
import com.dfoxtech.security.jwtauthentication.security.service.JWTService;
import com.dfoxtech.security.jwtauthentication.users.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    AuthRepository authRepository;
    AuthMapper authMapper;
    JWTService jwtService;
    public AuthService(AuthRepository authRepository, AuthMapper userMapperToEntity,JWTService jwtService) {
        this.authRepository = authRepository;
        this.authMapper = userMapperToEntity;
        this.jwtService = jwtService;
    }

    public void registerUser(RegisterDtoRequest request) {
        validExistingUser(request);
        validPassword(request);
        User user = authMapper.requestToEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        authRepository.save(user);
    }

    public AuthResponseLoggedDTO login(AuthRequestDto request) {
        User user =getUserByEmailOrUsername(request.username());
        if(passwordEncoder.matches(request.password(), user.getPassword())) {
            //generateToken
            String token=jwtService.generateToken(user);
            AuthResponseLoggedDTO reponse= new AuthResponseLoggedDTO(
                    "Welcome dear friend " + user.getUsername(),
                    token,
                    authMapper.toResponse(user)
            );
            return reponse;
        }else {
            throw new InvalidCredentialException("Invalid credential");
        }

    }

    private User getUserByEmailOrUsername(String request) {
        Optional<User> optionalUser = authRepository.findByEmail(request);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }else {
            optionalUser = authRepository.findByUsername(request);
            if(optionalUser.isPresent()) {
                return optionalUser.get();
            }else  {
                throw new InvalidCredentialException("Invalid username: " + request);
            }
        }
    }


    private void validPassword(RegisterDtoRequest request) {
        if(request.password().length() < 6) {
            throw new InvalidCredentialException("Password too short");
        }
        if(!request.password().equals(request.passwordConfirm())) {
            throw new InvalidCredentialException("Passwords do not match");
        }
        return;
    }

    private void validExistingUser(RegisterDtoRequest request) {
        if(authRepository.findByEmail(request.email()).isPresent()){
            throw new InvalidCredentialException("Email already in use");
        } else if (authRepository.findByUsername(request.username()).isPresent()) {
            throw new InvalidCredentialException("Username already in use");
        }
    }
}
