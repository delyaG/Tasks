package ru.itis.lab.services;

import ru.itis.lab.models.User;

public interface SignUpService {
    void signUp(String login, String password);
}
