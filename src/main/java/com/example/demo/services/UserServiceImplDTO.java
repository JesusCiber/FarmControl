package com.example.demo.services;

import com.example.demo.DTO.UserDTO;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImplDTO implements UserServiceDTO {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User(null, userDTO.getName(), userDTO.getPhoneNumber(), userDTO.getEmail(), "hashed-password");
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPhoneNumber());
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByEmail(String mail) {
        return Optional.empty();
    }
}