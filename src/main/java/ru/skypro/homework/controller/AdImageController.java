package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.impl.AdsService;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
@Tag(name = "Изображения объявлений")
public class AdImageController {
    private static final Logger log = LoggerFactory.getLogger(AdImageController.class);

    private final AdsService adsService;

    public AdImageController(AdsService adsService) {
        this.adsService = adsService;
    }

    @GetMapping("/{adImageId}")
    @Operation(summary = "Загрузка изображения объявления")
    public ResponseEntity<byte[]> getImageFromFs(@PathVariable("adImageId") String adImageId) throws IOException {
        return ResponseEntity.ok(adsService.getImageFromFs(adImageId));
    }
//
//    private ResponseEntity<byte[]> getEntityImage(Pair<byte[], String> imageData) {
//        byte[] data = imageData.getFirst();
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentLength(data.length)
//                .contentType(MediaType.parseMediaType(imageData.getSecond()))
//                .body(data);
//    }
}
