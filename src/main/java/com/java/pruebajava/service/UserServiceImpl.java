package com.java.pruebajava.service;

import com.java.pruebajava.dto.UserRequestDTO;
import com.java.pruebajava.dto.UserResponseDTO;
import com.java.pruebajava.entity.Phone;
import com.java.pruebajava.entity.User;
import com.java.pruebajava.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Value("${regex.password}")
    private String passwordRegex;

    @Override
    @Transactional
    public UserResponseDTO registerUser(UserRequestDTO dto) {
        userRepository.findByEmail(dto.getEmail()).ifPresent(u -> {
            throw new RuntimeException("El correo ya registrado");
        });

        LocalDateTime now = LocalDateTime.now();
        String token = UUID.randomUUID().toString();

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .created(now)
                .modified(now)
                .lastLogin(now)
                .token(token)
                .isActive(true)
                .build();

        List<Phone> phones = dto.getPhones().stream().map(p ->
                Phone.builder()
                        .number(p.getNumber())
                        .citycode(p.getCitycode())
                        .contrycode(p.getContrycode())
                        .user(user)
                        .build()
        ).toList();

        user.setPhones(phones);
        userRepository.save(user);

        return buildResponse(user);
    }

    private UserResponseDTO buildResponse(User user) {
        return new UserResponseDTO(
                user.getId().toString(),
                user.getCreated().toString(),
                user.getModified().toString(),
                user.getLastLogin().toString(),
                user.getToken(),
                user.isActive()
        );
    }
}
