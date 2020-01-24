package ru.itis.lab.services;

import ru.itis.lab.models.User;

public interface ProfileService {
    User getUser(Long id);
}
