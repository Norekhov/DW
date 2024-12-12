package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdListDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;

@Component
public class AdMapper {
    public static AdDto toDto(Ad ad) {
        AdDto adDto = new AdDto();
        adDto.setPk(ad.getPk());
        adDto.setTitle(ad.getTitle());
        adDto.setPrice(ad.getPrice());
        adDto.setAuthor(ad.getUser().getId());
        return adDto;
    }
    public static Ad toAd(AdDto adDto, User user) {
        Ad ad = new Ad();
        ad.setPk(adDto.getPk());
        ad.setTitle(adDto.getTitle());
        ad.setPrice(adDto.getPrice());
        ad.setUser(user);
        ad.setImage(adDto.getImage());
        return ad;
    }

    public static ExtendedAdDto toExtendedAdDto(Ad from) {
        ExtendedAdDto to = new ExtendedAdDto();
        to.setPk(from.getPk());
        to.setTitle(from.getTitle());
        to.setPrice(from.getPrice());
        to.setDescription(from.getAdText());
        to.setAuthorFirstName(from.getUser().getFirstName());
        to.setAuthorLastName(from.getUser().getLastName());
        to.setEmail(from.getUser().getUsername());
        to.setPhone(from.getUser().getPhone());
        return to;

    }
}
