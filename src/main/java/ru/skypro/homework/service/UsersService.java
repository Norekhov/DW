package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

@Service
public class UsersService {

    public String setPassword(NewPasswordDto newPasswordDto) {
        return null;
    }

    public UserDto getUser() {
        return null;
    }

    public String updateUser(UpdateUserDto updateUserDto) {
        return null;
    }

    public String updateUserImage(MultipartFile image) {
        return null;
    }
}
