package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

@Service
public class AdsService {

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

    public void updateAd(Integer id, CreateOrUpdateAdDto ad) {
    }

    public void removeAd(Integer id) {
    }
}
