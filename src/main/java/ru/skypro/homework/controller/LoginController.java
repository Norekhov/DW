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
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.service.AuthenticationProviderService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@Tag(name = "Авторизация")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final AuthenticationProviderService authService;

    public LoginController(AuthenticationProviderService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Авторизация пользователя")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        if (!authService.login(loginDto)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
    }
}
