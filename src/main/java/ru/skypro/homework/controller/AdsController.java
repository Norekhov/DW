package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdsService;

@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {

    @Autowired
    private AdsService adsService;

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public Ads getAllAds() {
        return adsService.getAllAds();
    }

    @PostMapping
    @Operation(summary = "Добавление объявления")
    public Ad addAd(@RequestParam("image") MultipartFile image,
                    @RequestBody CreateOrUpdateAd ad) {
        return adsService.addAd(image, ad);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ExtendedAd getAdById(@PathVariable Integer id) {
        return adsService.getAdById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    public String updateAd(@PathVariable Integer id,
                           @RequestBody CreateOrUpdateAd ad) {
        adsService.updateAd(id, ad);
        return "Объявление успешно обновлено";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public String removeAd(@PathVariable Integer id) {
        adsService.removeAd(id);
        return "Объявление успешно удалено";
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    public Ads getUserAds() {
        return adsService.getUserAds();
    }

    @PatchMapping("/{id}/image")
    @Operation(summary = "Обновление картинки объявления")
    public String updateUserImage(@PathVariable Integer id,
                                  @RequestBody CreateOrUpdateAd ad) {
        return null;
    }


}
