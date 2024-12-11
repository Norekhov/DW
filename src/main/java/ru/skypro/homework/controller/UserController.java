package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.UserAvatarService;
import ru.skypro.homework.service.CustomUserDetailsManager;
import ru.skypro.homework.mapper.UserMapper;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {

    private final UserAvatarService userAvatarService;
    private final CustomUserDetailsManager userService;

    public UserController(UserAvatarService userAvatarService, CustomUserDetailsManager userService) {
        this.userAvatarService = userAvatarService;
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
        return UserMapper.toApi(userDetailsManager.getCurrentUser());
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    public UpdateUserDto updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(updateUserDto);
    }

    @PatchMapping("/me/image")
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    public void updateUserAvatar(@RequestParam("image") MultipartFile image) throws IOException {
        userAvatarService.updateUserAvatar(image);
    }
}
