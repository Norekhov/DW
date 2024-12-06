package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.CustomUserDetailsManager;


@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {

    private final CustomUserDetailsManager userService;

    public UserController(CustomUserDetailsManager userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    public void setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        userService.changePassword(newPasswordDto);
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    public UserDto getUser() {
        UserMapper mapper = new UserMapper();
        return mapper.toDto((User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    public String updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(updateUserDto);
    }

    @PatchMapping("/me/image")
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    public String updateUserImage(@RequestParam("image") MultipartFile image) {
        return userService.updateUserImage(image);
    }
}
