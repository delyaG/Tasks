package ru.itis.lab.services;

import ru.itis.Component;
import ru.itis.lab.models.User;
import ru.itis.lab.repositories.UserRepository;

import java.util.Optional;

public class ProfileServiceImpl implements ProfileService, Component {

    private UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new IllegalStateException("no such user"));
        return user;
    }
}
