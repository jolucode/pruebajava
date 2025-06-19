package com.java.pruebajava.controller;

import com.java.pruebajava.dto.UserRequestDTO;
import com.java.pruebajava.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody UserRequestDTO dto) {
        try {
            return ResponseEntity.ok(userService.registerUser(dto));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(new ErrorMessage(ex.getMessage()));
        }
    }

    record ErrorMessage(String mensaje) {}
}