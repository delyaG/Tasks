package ru.itis.lab.config;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionConfig {

    private String username;
    private String password;
    private String url;
    private String DRIVER_NAME;

    private static ConnectionConfig instance;

    public ConnectionConfig() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        this.DRIVER_NAME = properties.getProperty("db.DRIVER_NAME");
        this.username = properties.getProperty("db.username");
        this.password = properties.getProperty("db.password");
        this.url = properties.getProperty("db.url");
    }

    public Connection getConnection() {
        try {
            Class.forName(this.DRIVER_NAME);
            return  DriverManager.getConnection(this.url, this.username, this.password);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static ConnectionConfig getInstance() {
        if (instance == null) {
            instance = new ConnectionConfig();
        }
        return instance;
    }
}