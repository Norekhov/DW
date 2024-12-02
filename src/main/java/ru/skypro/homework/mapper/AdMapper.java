package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.repository.UserRepository;

@Component
public class AdMapper {
    private final UserRepository userRepository;

    public AdMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AdDto toDto(Ad ad) {
        AdDto adDto = new AdDto();
        adDto.setPk(ad.getPk());
        adDto.setTitle(ad.getTitle());
        adDto.setPrice(ad.getPrice());
        adDto.setAuthor(ad.getUser().getId());
        return adDto;
    }
    public Ad toAd(AdDto adDto) {
        Ad ad = new Ad();
        ad.setPk(adDto.getPk());
        ad.setTitle(adDto.getTitle());
        ad.setPrice(adDto.getPrice());
        ad.setUser(userRepository.findById(adDto.getAuthor()).orElseThrow());
        return ad;
    }
}
