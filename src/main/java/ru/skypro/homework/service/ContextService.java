package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.ContextException;
import ru.skypro.homework.exception.UserException;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

@Service
public class ContextService {

    private final UserRepository userRepository;


    public ContextService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails getRecognizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ContextException("Распознанный пользователь не найден");
        }
        return (UserDetails) authentication.getPrincipal();
    }

    public User getRecognizedUserFromDb() {
        String username = getRecognizedUser().getUsername();
        return userRepository.findByEmail(username)
                .orElseThrow(() -> {
                    return new UserException("Пользователь с электронной почтой: " + username + " не найден");
                });
    }
}
