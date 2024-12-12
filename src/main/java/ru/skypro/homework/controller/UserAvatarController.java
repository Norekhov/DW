package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.CustomUserDetailsManager;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/avatars")
@Tag(name = "Аватары пользователей")
public class UserAvatarController {
    private static final Logger log = LoggerFactory.getLogger(UserAvatarController.class);
    private final CustomUserDetailsManager userService;

    public UserAvatarController(CustomUserDetailsManager userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}",produces = {MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE})
    @Operation(summary = "Загрузка аватара пользователя")
    public ResponseEntity<byte[]> getAvatarFromFs(@PathVariable("id") String avatarId) throws IOException {
        log.atWarn().log("UserAvatarController.getAvatarFromFs " + avatarId);
        return ResponseEntity.ok().body(userService.getAvatarFromFs(avatarId));
    }
//
//    private ResponseEntity<byte[]> getEntityImage(Pair<byte[], String> avatarData ) {
//        byte[] data = avatarData.getFirst();
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentLength(data.length)
//                .contentType(MediaType.parseMediaType(avatarData.getSecond()))
//                .body(data);
//    }
}
