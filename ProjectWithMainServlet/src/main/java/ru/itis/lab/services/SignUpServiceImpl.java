package ru.itis.lab.services;

import ru.itis.Component;
import ru.itis.lab.models.User;
import ru.itis.lab.repositories.UserRepository;

public class SignUpServiceImpl implements SignUpService, Component {

    private UserRepository userRepository;

    @Override
    public void signUp(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        userRepository.save(user);
    }
}
