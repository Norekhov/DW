package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdsService;

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
    public AdsDto getAllAds() {
        return adsService.getAllAds();
    }

    @PostMapping
    @Operation(summary = "Добавление объявления")
    public AdDto addAd(@RequestParam("image") MultipartFile image,
                       @RequestBody CreateOrUpdateAdDto ad) {
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
    public AdsDto getUserAds() {
        return adsService.getUserAds();
    }

    @PatchMapping("/{id}/image")
    @Operation(summary = "Обновление картинки объявления")
    public String updateUserImage(@PathVariable Integer id,
                                  @RequestBody CreateOrUpdateAdDto ad) {
        return null;
    }


}
