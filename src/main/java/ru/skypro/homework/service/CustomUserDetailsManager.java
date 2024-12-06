package ru.skypro.homework.service;

import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;

public interface CustomUserDetailsManager extends UserDetailsManager {
    void changePassword(NewPasswordDto newPasswordDto);

    String updateUser(UpdateUserDto updateUserDto);

    String updateUserImage(MultipartFile image);
}
