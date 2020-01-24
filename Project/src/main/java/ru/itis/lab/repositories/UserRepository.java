package ru.itis.lab.repositories;

import ru.itis.lab.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLoginAndPassword(String login, String password);
}
