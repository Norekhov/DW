package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.service.CustomUserDetailsManager;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@Tag(name = "Регистрация")
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
    private final CustomUserDetailsManager userService;

    public RegisterController(CustomUserDetailsManager userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(summary = "Регистрация пользователя")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) throws IOException {
        if (!userService.userExists(registerDto.getUsername())) {
            log.info("Попытка зарегистрировать пользователя с неуникальным логином {}", registerDto.getUsername());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        log.info("Зарегистрирован новый пользователь {}", registerDto.getUsername());
        userService.createUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
