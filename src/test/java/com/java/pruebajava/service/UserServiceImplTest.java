package com.java.pruebajava.service;

import com.java.pruebajava.dto.PhoneDTO;
import com.java.pruebajava.dto.UserRequestDTO;
import com.java.pruebajava.dto.UserResponseDTO;
import com.java.pruebajava.entity.User;
import com.java.pruebajava.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequestDTO validRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Simular el valor del regex (aunque no se usa directamente)
        ReflectionTestUtils.setField(userService, "passwordRegex", ".*");

        PhoneDTO phone = new PhoneDTO();
        phone.setNumber("1234567");
        phone.setCitycode("1");
        phone.setContrycode("57");

        validRequest = new UserRequestDTO();
        validRequest.setName("Juan Rodriguez");
        validRequest.setEmail("jp@rodriguez.cl");
        validRequest.setPassword("Clave123");
        validRequest.setPhones(Collections.singletonList(phone));
    }

    @Test
    void registerUser_NewEmail_ShouldReturnUserResponseDTO() {
        when(userRepository.findByEmail("jp@rodriguez.cl")).thenReturn(Optional.empty());

        // Para simular que se guarda el usuario con ID
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        when(userRepository.save(userCaptor.capture())).thenAnswer(invocation -> {
            User userToSave = userCaptor.getValue();
            userToSave.setId(UUID.randomUUID());
            return userToSave;
        });

        UserResponseDTO response = userService.registerUser(validRequest);

        assertNotNull(response);
        assertTrue(response.isActive());
        assertNotNull(response.getId());
        assertNotNull(response.getToken());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void registerUser_ExistingEmail_ShouldThrowException() {
        when(userRepository.findByEmail("jp@rodriguez.cl"))
                .thenReturn(Optional.of(new User()));

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                userService.registerUser(validRequest)
        );

        assertEquals("El correo ya registrado", exception.getMessage());
        verify(userRepository, never()).save(any());
    }
}