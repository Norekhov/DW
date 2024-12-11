package ru.skypro.homework.mapper;

import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.User;

public class UserMapper {
    public static UserApiDto toApi(User user) {
        UserApiDto userApiDto = new UserApiDto();
        userApiDto.setId(user.getId());
        userApiDto.setEmail(user.getUsername());
        userApiDto.setFirstName(user.getFirstName());
        userApiDto.setLastName(user.getLastName());
        userApiDto.setPhone(user.getPhone());
        userApiDto.setRole(user.getRole());
        return userApiDto;
    }
    public static UpdateUserDto toUpdateUserDto(User from) {
        UpdateUserDto to = new UpdateUserDto();
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setPhone(from.getPhone());
        return to;
    }
    public static User toUser(UserApiDto userApiDto) {
        User user = new User();
        user.setId(userApiDto.getId());
        user.setUsername(userApiDto.getEmail());
        user.setFirstName(userApiDto.getFirstName());
        user.setLastName(userApiDto.getLastName());
        user.setPhone(userApiDto.getPhone());
        user.setRole(userApiDto.getRole());
        return user;
    }
    public static UserDetails toUserDetails(User user) {
        return new UserAuthenticationDto(user.getUsername(), user.getPassword(), user.getRole(), user.isEnabled());
    }
    public static UserDetails toUserDetails(RegisterDto registerDto) {
        return new UserAuthenticationDto(registerDto.getUsername(), registerDto.getPassword(), registerDto.getRole(), 1);
    }

    public static User toUser(RegisterDto registerDto) {

        return new User(null, registerDto.getUsername(), registerDto.getFirstName(), registerDto.getLastName()
                , registerDto.getPhone(), registerDto.getRole(),registerDto.getPassword(), 1);
    }

    public static User toUser(UserDetails user) {
        return new User(null, user.getUsername(), null, null, null, Role.USER, user.getPassword(), user.isEnabled()?1:0);
    }

    public static User toUser(User user, UpdateUserDto updateUserDto) {
        return new User(null,user.getUsername(), updateUserDto.getFirstName(), updateUserDto.getLastName(),
                updateUserDto.getPhone(), user.getRole(), user.getPassword(), user.isEnabled());
    }
}
