package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.CustomUserDetailsManager;
import ru.skypro.homework.mapper.UserMapper;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final CustomUserDetailsManager userService;

    public UserController(CustomUserDetailsManager userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    public void  setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        userService.changePassword(newPasswordDto);
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    public UserApiDto getUser() {
        log.atWarn().log("UserController.getMe ");
        return UserMapper.toApi(userService.getCurrentUser());
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    public UpdateUserDto updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(updateUserDto);
    }

    @PatchMapping("/me/image")
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    public void updateUserAvatar(@RequestParam("image") MultipartFile image) throws IOException {
        userService.updateUserAvatar(image);
    }
}
