package ru.itis.lab.repositories;

import ru.itis.Component;
import ru.itis.lab.config.ConnectionConfig;
import ru.itis.lab.models.Role;
import ru.itis.lab.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository, Component {

    private final ConnectionConfig CONNECTION_CONFIG = ConnectionConfig.getInstance();
    private final String SAVE = "INSERT INTO public.pg_user (login, password, role) VALUES (?, ?, ?)";
    private final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM public.pg_user WHERE login = ? AND password = ? ";
    private final String FIND_BY_ID = "SELECT * FROM public.pg_user WHERE id = ?";

    @Override
    public void save(User model) {
        try (Connection connection = CONNECTION_CONFIG.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            statement.setString(1, model.getLogin());
            statement.setString(2, model.getPassword());
            statement.setString(3, model.getRole().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(Long aLong) {
        User user = null;
        try (Connection connection = CONNECTION_CONFIG.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, aLong);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                user = new User();
                user.setLogin(set.getString("login"));
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        User user = null;
        try (Connection connection = CONNECTION_CONFIG.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                user = new User();
                user.setId(set.getLong("id"));
                user.setLogin(set.getString("login"));
                user.setRole(Role.valueOf(set.getString("role")));
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
