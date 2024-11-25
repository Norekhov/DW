package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

@Service
public class AdsService {

    public Ads getAllAds() {
        return null;
    }

    public Ad addAd(MultipartFile image, CreateOrUpdateAd ad) {
        return null;
    }

    public ExtendedAd getAdById(Integer id) {
        return null;
    }

    public Ads getUserAds() {
        return null;
    }

    public User updateUserImage(Integer id, CreateOrUpdateAd ad) {
        return null;
    }

    public void updateAd(Integer id, CreateOrUpdateAd ad) {
    }

    public void removeAd(Integer id) {
    }
}
