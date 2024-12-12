package ru.skypro.homework.service;

import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.model.User;

public interface CustomUserDetailsManager extends UserDetailsManager {
    void createUser(RegisterDto registerDto);

    void updateUser(User user);

    void changePassword(NewPasswordDto newPasswordDto);

    UpdateUserDto updateUser(UpdateUserDto updateUserDto);

    String updateUserImage(MultipartFile image);

    User getCurrentUser();
}
