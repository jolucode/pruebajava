package com.java.pruebajava.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.pruebajava.dto.PhoneDTO;
import com.java.pruebajava.dto.UserRequestDTO;
import com.java.pruebajava.dto.UserResponseDTO;
import com.java.pruebajava.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerSuccessTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserRequestDTO validRequest;

    @BeforeEach
    void setUp() {
        PhoneDTO phone = new PhoneDTO();
        phone.setNumber("1234567");
        phone.setCitycode("1");
        phone.setContrycode("57");

        validRequest = new UserRequestDTO();
        validRequest.setName("Juan Rodriguez");
        validRequest.setEmail("jp@rodriguez.cl");
        validRequest.setPassword("Clave123");
        validRequest.setPhones(Collections.singletonList(phone));

        Mockito.when(userService.registerUser(Mockito.any())).thenReturn(
                new UserResponseDTO("uuid", "created", "modified", "lastLogin", "token", true)
        );
    }

    @Test
    void registerValidUser_ShouldReturn200() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(validRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void registerDuplicateEmail_ShouldReturn400() throws Exception {
        Mockito.when(userService.registerUser(Mockito.any()))
                .thenThrow(new RuntimeException("El correo ya registrado"));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(validRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(
                        org.springframework.test.web.servlet.result.MockMvcResultMatchers
                                .jsonPath("$.mensaje")
                                .value("El correo ya registrado")
                );
    }
}
