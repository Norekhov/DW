package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
