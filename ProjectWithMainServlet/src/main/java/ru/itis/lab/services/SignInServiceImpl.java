package ru.itis.lab.services;


import ru.itis.Component;
import ru.itis.lab.models.User;
import ru.itis.lab.repositories.UserRepository;

import java.util.Optional;

public class SignInServiceImpl implements SignInService, Component {

    private UserRepository userRepository;

    @Override
    public User signIn(String login, String password) {
        Optional<User> userOptional = userRepository.findByLoginAndPassword(login, password);
        User user = userOptional.orElseThrow(() -> new IllegalStateException("user not found"));
        return user;
    }
}
