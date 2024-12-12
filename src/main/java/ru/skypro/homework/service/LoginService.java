package ru.skypro.homework.service;

import ru.skypro.homework.dto.LoginDto;

public interface LoginService {
    boolean login(LoginDto loginDto);

    boolean login(String username, String password);
}
