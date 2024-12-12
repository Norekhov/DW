package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.impl.AdsService;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
@Tag(name = "Изображения объявлений")
public class AdImageController {

    private final AdsService adsService;

    public AdImageController(AdsService adsService) {
        this.adsService = adsService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Загрузка изображения объявления")
    public ResponseEntity<byte[]> getImageFromFs(@PathVariable("id") String pathForEndpoint) throws IOException {
        return ResponseEntity.ok(adsService.getImageFromFs(pathForEndpoint));
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
