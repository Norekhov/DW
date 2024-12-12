package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.impl.UserAvatarService;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/avatar")
@Tag(name = "Аватары пользователей")
public class UserAvatarController {

    private final UserAvatarService userAvatarService;

    public UserAvatarController(UserAvatarService userAvatarService) {
        this.userAvatarService = userAvatarService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Загрузка аватара пользователя")
    public ResponseEntity<byte[]> getAvatarFromFs(@PathVariable("id") String pathForEndpoint) throws IOException {
        Pair<byte[], String> avatarData  = userAvatarService.getAvatarFromFs(pathForEndpoint);
        return getEntityImage(avatarData );
    }

    private ResponseEntity<byte[]> getEntityImage(Pair<byte[], String> avatarData ) {
        byte[] data = avatarData .getFirst();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(data.length)
                .contentType(MediaType.parseMediaType(avatarData.getSecond()))
                .body(data);
    }
}
