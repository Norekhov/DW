package ru.skypro.homework.service;

import org.springframework.security.authentication.AuthenticationProvider;
import ru.skypro.homework.dto.RegisterDto;

public interface AuthenticationProviderService extends AuthenticationProvider {
    boolean login(String userName, String password);
    boolean register(RegisterDto registerDto);
}
