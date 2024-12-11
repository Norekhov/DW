package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Comment;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByAdPk(Integer adId);

    void deleteByAdPkAndPk(Integer adId, Integer pk);

    Comment findByAdPkAndPk(Integer adId, Integer pk);

    void deleteByAdPk(Integer adId);

    boolean existsByAdPkAndPk(Integer adId, Integer commentId);
}
