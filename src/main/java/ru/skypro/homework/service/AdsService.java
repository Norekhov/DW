package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.Mappers;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.AdPictureRepository;
import ru.skypro.homework.repository.AdRepository;

import java.util.List;


@Service
public class AdsService {

    private final AdRepository adRepository;
    private final AdPictureRepository adPictureRepository;
    private final AdPictureService adPictureService;
    private final CommentService commentService;
    private final Mappers mappers;

    public AdsService(AdRepository adRepository,
                      AdPictureRepository adPictureRepository,
                      AdPictureService adPictureService,
                      CommentService commentService,
                      Mappers mappers) {
        this.adRepository = adRepository;
        this.adPictureRepository = adPictureRepository;
        this.adPictureService = adPictureService;
        this.commentService = commentService;
        this.mappers = mappers;
    }


    public AdsDto getAllAds() {
        return null;
    }

    public AdDto addAd(MultipartFile image, CreateOrUpdateAdDto ad) {
        return null;
    }

    public ExtendedAdDto getAdById(Integer id) {
        return null;
    }

    public AdsDto getUserAds() {
        return null;
    }

    public UserDto updateUserImage(Integer id, CreateOrUpdateAdDto ad) {
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
