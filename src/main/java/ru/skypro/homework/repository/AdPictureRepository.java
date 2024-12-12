package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AdPicture;

import java.util.List;

@Repository
public interface AdPictureRepository extends JpaRepository<AdPicture, Integer> {

    AdPicture findByAdPk(Integer adId);

    List<AdPicture> findAllByAdPkIn(List<Integer> adPk);

    void deleteByAdPk(Integer adId);
}
