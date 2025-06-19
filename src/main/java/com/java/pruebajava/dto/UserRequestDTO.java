package com.java.pruebajava.dto;

import com.java.pruebajava.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {

    @NotBlank(message = "El name no puede estar vacío")
    private String name;

    @NotBlank
    @Email(message = "Formato general inválido")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.cl$", message = "Solo se permiten correos con dominio .cl")
    private String email;

    @NotBlank
    @ValidPassword
    private String password;

    private List<PhoneDTO> phones;
}