package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.UsersService;


@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    public String setPassword(@RequestBody NewPassword newPassword) {
        return usersService.setPassword(newPassword);
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    public User getUser() {
        return usersService.getUser();
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    public String updateUser(@RequestBody UpdateUser updateUser) {
        return usersService.updateUser(updateUser);
    }

    @PatchMapping("/me/image")
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    public String updateUserImage(@RequestParam("image") MultipartFile image) {
        return usersService.updateUserImage(image);
    }
}
