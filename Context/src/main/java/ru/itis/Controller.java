package ru.itis;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    void handle(HttpServletRequest request, HttpServletResponse response, ServletContext context);
}
