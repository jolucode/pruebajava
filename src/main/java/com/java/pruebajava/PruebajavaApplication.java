package com.java.pruebajava;

import com.java.pruebajava.entity.User;
import com.java.pruebajava.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class PruebajavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebajavaApplication.class, args);
	}
}
