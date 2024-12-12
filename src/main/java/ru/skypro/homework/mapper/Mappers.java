//package ru.skypro.homework.mapper;
//
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import ru.skypro.homework.dto.*;
//import ru.skypro.homework.model.Ad;
//import ru.skypro.homework.model.Comment;
//import ru.skypro.homework.model.User;
//
//@Mapper(componentModel = "spring")
//public interface Mappers {
//
//    @Mapping(target = "image", ignore = true)
//    UserDto toUserDto(User user);
//
//
//    @Mapping(target = "email", source = "username")
//    @Mapping(target = "id", ignore = true)
//    User toUser(RegisterDto registerDto);
//
//    UpdateUserDto toUpdateUserDto(User user);
//
//    @Mapping(source = "user.id", target = "author")
//    @Mapping(source = "user.firstName", target = "authorFirstName")
//    CommentDto toCommentDto(Comment comment);
//
//    @Mapping(expression = "java(System.currentTimeMillis())", target = "createdAt")
//    Comment toComment(CreateOrUpdateCommentDto commentDto);
//
//    @Mapping(source = "user.firstName", target = "authorFirstName")
//    @Mapping(source = "user.lastName", target = "authorLastName")
//    @Mapping(source = "user.email", target = "email")
//    @Mapping(source = "user.phone", target = "phone")
//    ExtendedAdDto toExtendedAdDto(Ad ad);
//
//    @Mapping(source = "user.id", target = "author")
//    AdDto toAdDto(Ad ad);
//
//    Ad toAds(CreateOrUpdateAdDto adDto);
//}
