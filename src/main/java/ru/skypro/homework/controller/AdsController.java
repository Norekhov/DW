package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdListDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.impl.AdsService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {
    private static final Logger log = LoggerFactory.getLogger(AdsController.class);
    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public AdListDto getAllAds() {
        return adsService.getAllAds();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    public ResponseEntity<AdDto> addAd(@RequestParam("image") MultipartFile image, @RequestPart("properties") CreateOrUpdateAdDto ad) {
        log.info("Добавление объявления {}", ad.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(adsService.addAd(image, ad));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ExtendedAdDto getAdById(@PathVariable Integer id) {
        return adsService.getAdById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    public String updateAd(@PathVariable Integer id, @RequestBody CreateOrUpdateAdDto ad) {
        log.info("Обновление информации об объявлении {}", ad.getTitle());
        return adsService.updateAd(id, ad);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public ResponseEntity<?> removeAd(@PathVariable Integer id) {
        log.info("Удаление объявления {}", id);
        HttpStatus httpStatus = adsService.removeAd(id);
        return ResponseEntity.status(httpStatus).body(httpStatus.getReasonPhrase());
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    public AdListDto getUserAds() {
        return adsService.getUserAds();
    }

    @PatchMapping("/{id}/image")
    @Operation(summary = "Обновление изображения объявления")
    public String updateAdImage(@PathVariable Integer id, @RequestParam("image") MultipartFile image) {
        log.info("Обновление изображения объявления {}", id);
        return adsService.updateAdImage(id, image);
    }
}