package com.java.pruebajava.service;

import com.java.pruebajava.dto.UserRequestDTO;
import com.java.pruebajava.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO registerUser(UserRequestDTO dto);
}