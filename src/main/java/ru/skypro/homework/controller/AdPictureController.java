package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.AdPictureService;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
@Tag(name = "Картинки объявлений")
public class AdPictureController {

    private final AdPictureService adPictureService;

    public AdPictureController(AdPictureService adPictureService) {
        this.adPictureService = adPictureService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Загрузка картинки объявления")
    public ResponseEntity<byte[]> getImageFromFs(@PathVariable("id") String pathForEndpoint) throws IOException {
        Pair<byte[], String> imageData = adPictureService.getImageFromFs(pathForEndpoint);
        return getEntityImage(imageData);
    }

    private ResponseEntity<byte[]> getEntityImage(Pair<byte[], String> imageData) {
        byte[] data = imageData.getFirst();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(data.length)
                .contentType(MediaType.parseMediaType(imageData.getSecond()))
                .body(data);
    }
}
