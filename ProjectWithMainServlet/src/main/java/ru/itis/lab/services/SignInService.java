package ru.itis.lab.services;

import ru.itis.lab.models.User;

public interface SignInService {
    User signIn(String login, String password);
}
