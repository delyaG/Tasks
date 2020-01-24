package ru.itis.lab.controllers;

import ru.itis.Controller;
import ru.itis.Mapping;
import ru.itis.lab.models.Role;
import ru.itis.lab.models.User;
import ru.itis.lab.services.ProfileService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Mapping("/profile")
public class ProfileController implements Controller {

    ProfileService service;

    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       ServletContext servletContext) {
        String method = httpServletRequest.getMethod();
        if (method.equals("GET")) {
            doGet(httpServletRequest, httpServletResponse);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("id");
        Role role = (Role) session.getAttribute("role");
        User user = service.getUser(userId);
        req.setAttribute("login", user.getLogin());
        req.setAttribute("role", role.toString());
        try {
            req.getRequestDispatcher("/profile.ftl").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
