package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

@Service
public class UsersService {

    public String setPassword(NewPassword newPassword) {
        return null;
    }

    public User getUser() {
        return null;
    }

    public String updateUser(UpdateUser updateUser) {
        return null;
    }

    public String updateUserImage(MultipartFile image) {
        return null;
    }
}