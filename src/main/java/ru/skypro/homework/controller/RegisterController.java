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
import ru.skypro.homework.exception.UserAlreadyExistsException;
import ru.skypro.homework.service.CustomUserDetailsManager;

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
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        try {
            userService.createUser(registerDto);
        } catch (UserAlreadyExistsException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        log.info("Зарегистрирован новый пользователь {}", registerDto.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
