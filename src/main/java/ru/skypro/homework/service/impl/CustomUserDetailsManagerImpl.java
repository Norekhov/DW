package ru.skypro.homework.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.exception.UserAlreadyExistsException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CustomUserDetailsManager;

import java.util.Optional;

@Service
public class CustomUserDetailsManagerImpl implements CustomUserDetailsManager {

    private final UserRepository userRepository;

    public CustomUserDetailsManagerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("------------loadUserByUsername------------loadUserByUsername------------loadUserByUsername------------loadUserByUsername------------loadUserByUsername------------loadUserByUsername------------loadUserByUsername------------loadUserByUsername------------loadUserByUsername------------loadUserByUsername------------loadUserByUsername------------loadUserByUsername");
        UserDetails userDetails = UserMapper.toUserDetails(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException(username)));
        System.out.println(userDetails.getUsername()+" "+userDetails.getPassword()+" "+userDetails.getAuthorities());
        return userDetails;
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void createUser(RegisterDto registerDto) {
        if (userExists(registerDto.getUsername())) {
            throw new UserAlreadyExistsException(registerDto.getUsername());
        }
        userRepository.save(UserMapper.toUser(registerDto));
    }

    @Override
    public void createUser(UserDetails user) {
        if (userExists(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername());
        }
        userRepository.save(UserMapper.toUser(user));
    }

    @Override
    public void updateUser(UserDetails user) {
        updateUser(UserMapper.toUser(user));
    }
    @Override
    public void updateUser(User user) {
        if (!userExists(user.getUsername())) {
            throw new UsernameNotFoundException(user.getUsername());
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        Optional<User> user= userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        userRepository.delete(user.get());
    }

    @Override
    public void changePassword(NewPasswordDto newPasswordDto) {
        changePassword(newPasswordDto.getCurrentPassword(), newPasswordDto.getNewPassword());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

        if(encoder.matches(oldPassword,getCurrentUser().getPassword())) {
            User user = getCurrentUser();
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
        }
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUserDto) {
        User user = getCurrentUser();
        UserMapper.toUser(user, updateUserDto);
        userRepository.save(user);
        return updateUserDto;
    }

    @Override
    public String updateUserImage(MultipartFile image) {
        return "";
    }

    @Override
    public User getCurrentUser() {
        System.out.println("------------getCurrentUser------------getCurrentUser------------getCurrentUser------------getCurrentUser------------getCurrentUser------------getCurrentUser------------getCurrentUser------------getCurrentUser------------getCurrentUser------------getCurrentUser------------getCurrentUser------------getCurrentUser");
        UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(
                () -> new UsernameNotFoundException(userDetails.getUsername()));
        System.out.println(user);

        return user;
    }

}
