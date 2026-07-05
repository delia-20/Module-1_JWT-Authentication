package com.dfoxtech.security.jwtauthentication.users.service;

import com.dfoxtech.security.jwtauthentication.users.dto.UserCreateDto;
import com.dfoxtech.security.jwtauthentication.users.dto.UserReponseDTO;
import com.dfoxtech.security.jwtauthentication.users.entity.User;
import com.dfoxtech.security.jwtauthentication.users.mapper.UserMapper;
import com.dfoxtech.security.jwtauthentication.users.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserService {
    static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    UserRepository userRepository;
    UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    public User createUser(UserCreateDto userDtoRequest) {
        User user=userMapper.requestToUser(userDtoRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public UserReponseDTO getUserByUsername(String username) {
        User user=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return userMapper.responseToUserCreateDto(user);
    }
    public UserReponseDTO getUserByEmail(String email) {
        User user=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return userMapper.responseToUserCreateDto(user);
    }
    public void deleteUserByEmail(String email) {
        User user=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
        userRepository.delete(user);
    }
    public void deleteById(UUID id) {
        User user=userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found"));
        userRepository.delete(user);
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users= (ArrayList<User>) userRepository.findAll();
        return users;
    }
}
