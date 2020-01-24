package ru.itis;

public interface MyContext {
    <T> T getComponent(String name);
    <T> T getComponent(String name, String url);
}
