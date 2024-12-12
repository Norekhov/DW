package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdPictureRepository;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.CustomUserDetailsManager;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdsService {
    private final CustomUserDetailsManager userService;

    private final AdRepository adRepository;
    private final AdPictureRepository adPictureRepository;
    private final AdPictureService adPictureService;
    private final CommentService commentService;

    public AdsService(CustomUserDetailsManager userService, AdRepository adRepository, AdPictureRepository adPictureRepository, AdPictureService adPictureService, CommentService commentService
    ) {
        this.userService = userService;
        this.adRepository = adRepository;
        this.adPictureRepository = adPictureRepository;
        this.adPictureService = adPictureService;
        this.commentService = commentService;
//        this.mappers = mappers;
    }


    public AdListDto getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return new AdListDto(ads.size(),ads.stream().map(AdMapper::toDto).collect(Collectors.toList()));
    }

    public AdDto addAd(MultipartFile image, CreateOrUpdateAdDto ad) {
        return null;
    }

    public ExtendedAdDto getAdById(Integer id) {
        return null;
    }

    public AdListDto getUserAds() {
        Integer id = userService.getCurrentUser().getId();
        List<Ad> ads = adRepository.findByUserId(id);
        return new AdListDto(ads.size(),ads.stream().map(AdMapper::toDto).collect(Collectors.toList()));
    }

    public UserApiDto updateUserImage(Integer id, CreateOrUpdateAdDto ad) {
        return null;
    }

    public String updateAd(Integer id, CreateOrUpdateAdDto ad) {
        return null;
    }

    public void removeAd(Integer id) {
    }

    public String updateAdPicture(Integer id, MultipartFile image) {
        return "";
    }
}
