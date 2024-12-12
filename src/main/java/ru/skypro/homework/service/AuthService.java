package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterDto;

import java.io.IOException;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDto registerDto) throws IOException;
}
