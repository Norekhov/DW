package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserAvatar;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Repository
public interface UserAvatarRepository extends JpaRepository<UserAvatar, Integer> {

    Optional<UserAvatar> findByUserId(Integer userId);

    @Query("SELECT a FROM UserAvatar a WHERE a.user.id IN :userId")
    List<UserAvatar> findByUserIdIn(@Param("userId") Set<Integer> userId);
}
