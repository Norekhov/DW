package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CustomUserDetailsManager;

@Service
public class CustomUserDetailsManagerImpl implements CustomUserDetailsManager {

    private final UserRepository userRepository;

    public CustomUserDetailsManagerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    public void changePassword(NewPasswordDto newPasswordDto) {
        changePassword(newPasswordDto.getCurrentPassword(), newPasswordDto.getNewPassword());
    }

    @Override
    public String updateUser(UpdateUserDto updateUserDto) {
        return "";
    }

    @Override
    public String updateUserImage(MultipartFile image) {
        return "";
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
