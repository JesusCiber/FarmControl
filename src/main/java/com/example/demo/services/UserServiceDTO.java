package com.example.demo.services;

import com.example.demo.DTO.UserDTO;
import com.example.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceDTO {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    Optional<User> findByEmail(String mail);
}
