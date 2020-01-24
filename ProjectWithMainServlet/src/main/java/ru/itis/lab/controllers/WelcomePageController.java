package ru.itis.lab.controllers;

import ru.itis.Controller;
import ru.itis.Mapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Mapping("/welcomePage")
public class WelcomePageController implements Controller {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       ServletContext servletContext) {
        try {
            httpServletResponse.getWriter().println("H");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
