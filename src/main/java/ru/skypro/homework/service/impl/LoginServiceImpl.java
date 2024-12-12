package ru.skypro.homework.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.service.CustomUserDetailsManager;
import ru.skypro.homework.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
    private final CustomUserDetailsManager userManager;

    public LoginServiceImpl(CustomUserDetailsManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public boolean login(LoginDto loginDto) {
        return login(loginDto.getUsername(), loginDto.getPassword());
    }

    @Override
    public boolean login(String username, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

        return encoder.matches(password, userManager.loadUserByUsername(username).getPassword());
    }
}
