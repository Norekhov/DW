package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdPictureService;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {

    private final AdPictureService adPictureService;
    private final AdsService adsService;

    public AdsController(AdPictureService adPictureService,
                         AdsService adsService) {
        this.adPictureService = adPictureService;
        this.adsService = adsService;
    }

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public AdsDto getAllAds() {
        return adsService.getAllAds();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    public AdDto addAd(@RequestParam("image") MultipartFile image,
                       @RequestPart("properties") CreateOrUpdateAdDto ad) {
        return adsService.addAd(image, ad);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ExtendedAdDto getAdById(@PathVariable Integer id) {
        return adsService.getAdById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    public String updateAd(@PathVariable Integer id,
                           @RequestBody CreateOrUpdateAdDto ad) {
        return adsService.updateAd(id, ad);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public void removeAd(@PathVariable Integer id) {
        adsService.removeAd(id);
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    public AdsDto getUserAds() {
        return adsService.getUserAds();
    }

    @PatchMapping("/{id}/image")
    @Operation(summary = "Обновление картинки объявления")
    public String updateAdPicture(@PathVariable Integer id,
                                  @RequestParam("image") MultipartFile image) throws IOException {
        return adsService.updateAdPicture(id, image);
    }
}
