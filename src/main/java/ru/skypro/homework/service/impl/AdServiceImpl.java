package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.config.ApplicationConfig;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdImageException;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.exception.UnauthorizedException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CustomUserDetailsManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class AdServiceImpl implements AdService {
    private static final Logger log = LoggerFactory.getLogger(AdServiceImpl.class);

    private final CustomUserDetailsManager userService;
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;

    public AdServiceImpl(CustomUserDetailsManager userService, AdRepository adRepository, CommentRepository commentRepository) {
        this.userService = userService;
        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public AdListDto getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return new AdListDto(ads.size(), ads.stream().map(AdMapper::toDto).collect(Collectors.toList()));
    }

    @Override
    public AdDto addAd(MultipartFile image, CreateOrUpdateAdDto ad) {
        Ad newAd = new Ad();
        newAd.setUser(userService.getCurrentUser());
        newAd.setTitle(ad.getTitle());
        newAd.setAdText(ad.getDescription());
        newAd.setPrice(ad.getPrice());
        String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
        String imageUrl = ApplicationConfig.getPathToAdImages() + UUID.randomUUID() + "." + extension;
        try {
            saveAdImage(image, imageUrl);
        } catch (IOException e) {
            log.error("Не удалось сохранить аватарку");
            return AdMapper.toDto(newAd);
        }
        newAd.setImageUrl(imageUrl);
        return AdMapper.toDto(adRepository.save(newAd));
    }

    @Override
    public void saveAdImage(MultipartFile image, String adImageUrl) throws IOException {
        Path parentDir = Path.of(".", ApplicationConfig.getPathToAdImages());
        if (!Files.exists(parentDir)) {
            try {
                Files.createDirectories(parentDir);
            } catch (IOException e) {
                AdServiceImpl.log.error("Не удалось создать директорию {}", parentDir);
            }
        }
        Path path = Path.of(".", adImageUrl);
        Files.write(path, image.getBytes());
    }

    @Override
    public ExtendedAdDto getAdById(Integer id) throws AdImageException{
        return adRepository.findById(id).map(AdMapper::toExtendedAdDto).orElseThrow(() -> new AdImageException("Изображение не найдено"));
    }

    @Override
    public AdListDto getUserAds() throws UnauthorizedException {
        Integer id = userService.getCurrentUser().getId();
        List<Ad> ads = adRepository.findByUserId(id);
        return new AdListDto(ads.size(), ads.stream().map(AdMapper::toDto).collect(Collectors.toList()));
    }

    @Override
    public AdDto updateAd(Integer adId, CreateOrUpdateAdDto ad) throws ForbiddenException {
        Ad updatedAd = adRepository.findById(adId).orElseThrow(() -> new EntityNotFoundException("Попытка обновить несуществующее объявление " + adId));
        Integer currentUserId = userService.getCurrentUser().getId();
        Integer adUserId = updatedAd.getUser().getId();
        if (!adUserId.equals(currentUserId)){
            throw new ForbiddenException("Попытка изменить чужое объявление");
        }
        updatedAd.setTitle(ad.getTitle());
        if (ad.getDescription()!=null) {
            updatedAd.setAdText(ad.getDescription());
        }
        updatedAd.setPrice(ad.getPrice());

        return AdMapper.toDto(adRepository.save(updatedAd));
    }

    @Override
    public void removeAd(Integer adId) throws EntityNotFoundException, ForbiddenException {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new EntityNotFoundException("Попытка удалить несуществующие объявление: " + adId));
        if (!checkAuthorization(ad)) {
            throw new ForbiddenException("Попытка удалить чужое объявление: " + adId);
        }
        commentRepository.deleteAll(commentRepository.findByAdPk(adId));
        if (ad.getImageUrl() != null) {
            removeAdImage(ad.getImageUrl());
        }
        adRepository.deleteById(adId);
    }

    @Override
    public void removeAdImage(String adImageUrl) {
        Path path = Path.of(".", adImageUrl);
        try {
            Files.delete(path);
        } catch (IOException e) {
            log.warn("Не удаётся удалить изображение объявления {}", path);
        }
    }
    @Override
    public byte[] updateAdImage(Integer adId, MultipartFile image) throws IOException, ForbiddenException {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new EntityNotFoundException("Попытка обновить изображение для несуществующего объявления" + adId));
        if (!checkAuthorization(ad)) {
            throw new ForbiddenException("Попытка обновить изображение чужого объявления");
        }
        if (ad.getImageUrl() != null && Files.exists(Path.of(".", ad.getImageUrl()))) {
            try {
                removeAdImage(ad.getImageUrl());
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        }
        String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
        String url = ApplicationConfig.getPathToAdImages() + UUID.randomUUID() + "." + extension;
        saveAdImage(image, url);
        ad.setImageUrl(url);
        adRepository.save(ad);
        return image.getBytes();
    }

    @Override
    public byte[] readAdImageFromFs(String adImageUrl) throws IOException {
        Path path = Path.of(".", ApplicationConfig.getPathToAdImages()).resolve(adImageUrl);
        if (!Files.exists(path)) {
            throw new FileNotFoundException(path.toString());
        }
        return Files.readAllBytes(path);
    }

    @Override
    public boolean checkAuthorization(Ad ad) {
        User currentUser = userService.getCurrentUser();
        return currentUser.getRole().equals(Role.ADMIN) || Objects.equals(currentUser.getId(), ad.getUser().getId());
    }
}
