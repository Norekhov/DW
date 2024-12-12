package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.config.ApplicationConfig;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdImageException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CustomUserDetailsManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class AdsService {
    private static final Logger log = LoggerFactory.getLogger(AdsService.class);

    private final CustomUserDetailsManager userService;

    private final AdRepository adRepository;
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    public AdsService(CustomUserDetailsManager userService, AdRepository adRepository, CommentService commentService, CommentRepository commentRepository) {
        this.userService = userService;
        this.adRepository = adRepository;
        this.commentService = commentService;
        this.commentRepository = commentRepository;
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
        newAd.setPrice(ad.getPrice());;
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

    public HttpStatus removeAd(Integer avatarId) {
        Optional<Ad> ad = adRepository.findById(avatarId);
        if (ad.isEmpty()) {
            log.info("Попытка удалить несуществующие объявление: {}", avatarId);
            return HttpStatus.NOT_FOUND;
        }
        if (!checkAuthorization(ad.get())) {
            log.warn("Попытка удалить чужое объявление: {}", avatarId);
            return HttpStatus.FORBIDDEN;
        }
        commentRepository.findByAdPk(avatarId).forEach(commentRepository::delete);
        String adImage = ad.get().getImage();
        if (adImage != null) {
            try {
                Files.delete(Path.of(".", ApplicationConfig.getPathToAdImages(), avatarId.toString()));
            } catch (IOException e) {
                throw new AdImageException("Не удаётся удалить изображение объявления " + adImage);
            }
            adRepository.deleteById(avatarId);
        }
        return HttpStatus.NO_CONTENT;
    }

    public byte[] updateAdImage(Integer adId, MultipartFile image) {
        return "";
    }

    @Override
    public byte[] updateUserAvatar(MultipartFile image) throws IOException {
        User user = getCurrentUser();
        if (user.getAvatar() != null) {
            log.info("Найден аватар, удаляю");
            deleteExistingAvatar(user);
        }
        String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
        user.setAvatar(UUID.randomUUID() + "." + extension);
        Path parentDir = Path.of(".", ApplicationConfig.getPathToAvatars());
        if (!Files.exists(parentDir)) Files.createDirectories(parentDir);
        Path path = parentDir.resolve(user.getAvatar());
        Files.write(path, image.getBytes());
        userRepository.save(user);
    }




    public byte[] getImageFromFs(String adImageId) throws IOException {
        Path path=resolvePathToAdImage(adImageId);
        if (!Files.exists(path)){
            log.info("Изображения нету");
            throw new FileNotFoundException(adImageId);
        }
        return Files.readAllBytes(path);
    }

    private boolean checkAuthorization(Ad ad) {
        User currentUser = userService.getCurrentUser();
        return currentUser.getRole().equals(Role.ADMIN) || Objects.equals(currentUser.getId(), ad.getUser().getId());
    }
    private Path resolvePathToAdImage(String adImageId) {
        return Path.of(".", ApplicationConfig.getPathToAdImages()).resolve(adImageId);
    }
}
