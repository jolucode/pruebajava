package com.java.pruebajava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private String id;
    private String created;
    private String modified;
    private String lastLogin;
    private String token;
    private boolean isActive;
}
