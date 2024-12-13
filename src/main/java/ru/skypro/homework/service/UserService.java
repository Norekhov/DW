//package ru.skypro.homework.service;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import ru.skypro.homework.dto.NewPasswordDto;
//import ru.skypro.homework.dto.UpdateUserDto;
//import ru.skypro.homework.dto.UserApiDto;
////import ru.skypro.homework.mapper.Mappers;
//import ru.skypro.homework.mapper.UserMapper;
//import ru.skypro.homework.model.User;
//import ru.skypro.homework.repository.UserAvatarRepository;
//import ru.skypro.homework.repository.UserRepository;
//
//@Service
//public class UserService {
//
////    private final Mappers mappers;
//    private final CustomUserDetailsManager customUserDetailsManager;
//    private final UserAvatarRepository userAvatarRepository;
//    private final UserRepository userRepository;
//    private final PasswordEncoder encoder;
//
//    public UserService(
////            Mappers mappers,
//            CustomUserDetailsManager customUserDetailsManager,
//                       UserAvatarRepository userAvatarRepository,
//                       UserRepository userRepository) {
////        this.mappers = mappers;
//        this.customUserDetailsManager = customUserDetailsManager;
//        this.userAvatarRepository = userAvatarRepository;
//        this.userRepository = userRepository;
//        this.encoder = new BCryptPasswordEncoder(10);
//    }
//
//    public UserApiDto getUser() {
//        User user = customUserDetailsManager.getCurrentUser();
//        UserApiDto userApi = UserMapper.toApi(user);
//
//        userAvatarRepository.findByUserId(user.getId())
//                .ifPresent(avatar -> userApi.setImage(avatar.getPathForEndpoint()));
//        return userApi;
//    }
//
//    public UpdateUserDto updateUser(UpdateUserDto updateUserDto) {
//        User user = customUserDetailsManager.getCurrentUser();
//
//        user.setFirstName(updateUserDto.getFirstName());
//        user.setLastName(updateUserDto.getLastName());
//        user.setPhone(updateUserDto.getPhone());
//
//        User updatedUser = userRepository.save(user);
//
//        return UserMapper.toUpdateUserDto(updatedUser);
//    }
//
//    public void setPassword(NewPasswordDto newPasswordDto) {
//        User user = customUserDetailsManager.getCurrentUser();
//        String newPassword = encoder.encode(newPasswordDto.getNewPassword());
//        user.setPassword(newPassword);
//        userRepository.save(user);
//    }
//}
