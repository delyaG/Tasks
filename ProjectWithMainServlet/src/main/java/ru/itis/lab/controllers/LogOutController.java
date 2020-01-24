package ru.itis.lab.controllers;

import ru.itis.Controller;
import ru.itis.Mapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Mapping("/logOut")
public class LogOutController implements Controller {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ServletContext servletContext) {
        String method = httpServletRequest.getMethod();
        if (method.equals("GET")) {
            doGet(httpServletRequest, httpServletResponse);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.invalidate();
        try {
            resp.sendRedirect("signIn");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
