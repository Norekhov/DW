package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdImageException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.CustomUserDetailsManager;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdsService {
    private final CustomUserDetailsManager userService;

    private final AdRepository adRepository;
    private final CommentService commentService;

    public AdsService(CustomUserDetailsManager userService, AdRepository adRepository, CommentService commentService) {
        this.userService = userService;
        this.adRepository = adRepository;
        this.commentService = commentService;
    }


    public AdListDto getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return new AdListDto(ads.size(), ads.stream().map(AdMapper::toDto).collect(Collectors.toList()));
    }

    public AdDto addAd(MultipartFile image, CreateOrUpdateAdDto ad) {
        Ad newAd = new Ad();
        newAd.setUser(userService.getCurrentUser());
        newAd.setTitle(ad.getTitle());
        newAd.setAdText(ad.getDescription());
        newAd.setPrice(ad.getPrice());
        Ad savedAd = adRepository.save(newAd);
        String filename = updateAdImage(savedAd.getPk(), image);
        return AdMapper.toDto(newAd);
    }

    public ExtendedAdDto getAdById(Integer id) {
        return adRepository.findById(id).map(AdMapper::toExtendedAdDto).orElseThrow(() -> new AdImageException("Изображение не найдено"));
    }

    public AdListDto getUserAds() {
        Integer id = userService.getCurrentUser().getId();
        List<Ad> ads = adRepository.findByUserId(id);
        return new AdListDto(ads.size(), ads.stream().map(AdMapper::toDto).collect(Collectors.toList()));
    }

    public String updateAd(Integer id, CreateOrUpdateAdDto ad) {
        return null;
    }

    public void removeAd(Integer id) {
    }

    public String updateAdImage(Integer id, MultipartFile image) {
        return "";
    }
    public byte[] getImageFromFs(String pathForEndpoint) {
        return null;
    }
}
