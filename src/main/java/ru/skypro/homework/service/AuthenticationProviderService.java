package ru.skypro.homework.service;

import org.springframework.security.authentication.AuthenticationProvider;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.dto.RegisterDto;

public interface AuthenticationProviderService extends AuthenticationProvider {
    boolean login(LoginDto loginDto);
    boolean login(String userName, String password);
    boolean register(RegisterDto registerDto);
}
