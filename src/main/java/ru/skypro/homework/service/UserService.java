package ru.skypro.homework.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.Mappers;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserAvatarRepository;
import ru.skypro.homework.repository.UserRepository;

@Service
public class UserService {

    private final Mappers mappers;
    private final ContextService contextService;
    private final UserAvatarRepository userAvatarRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(Mappers mappers,
                       ContextService contextService,
                       UserAvatarRepository userAvatarRepository,
                       UserRepository userRepository,
                       PasswordEncoder encoder) {
        this.mappers = mappers;
        this.contextService = contextService;
        this.userAvatarRepository = userAvatarRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserDto getUser() {
        User userDb = contextService.getRecognizedUserFromDb();
        UserDto userDto = mappers.toUserDto(userDb);

        userAvatarRepository.findByUserId(userDb.getId())
                .ifPresent(avatar -> userDto.setImage(avatar.getPathForEndpoint()));
        return userDto;
    }

    public UpdateUserDto updateUser(UpdateUserDto updateUserDto) {
        User userDb = contextService.getRecognizedUserFromDb();

        userDb.setFirstName(updateUserDto.getFirstName());
        userDb.setLastName(updateUserDto.getLastName());
        userDb.setPhone(updateUserDto.getPhone());

        User updatedUser = userRepository.save(userDb);

        return mappers.toUpdateUserDto(updatedUser);
    }

    public void setPassword(NewPasswordDto newPasswordDto) {
        User userDb = contextService.getRecognizedUserFromDb();
        String newPassword = encoder.encode(newPasswordDto.getNewPassword());
        userDb.setPassword(newPassword);
        userRepository.save(userDb);
    }
}
