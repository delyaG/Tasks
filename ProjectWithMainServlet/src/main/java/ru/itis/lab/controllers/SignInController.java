package ru.itis.lab.controllers;

import ru.itis.Controller;
import ru.itis.Mapping;
import ru.itis.lab.models.User;
import ru.itis.lab.services.SignInService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Mapping("/signIn")
public class SignInController implements Controller {

    SignInService service;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       ServletContext servletContext) {
        String method = httpServletRequest.getMethod();
        if (method.equals("GET")) {
            doGet(httpServletRequest, httpServletResponse);
        } else if (method.equals("POST")) {
            doPost(httpServletRequest, httpServletResponse);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/signIn.ftl").forward(req, resp);
//            RequestDispatcher requestDispatcher = request.getSession().getServletContext().getRequestDispatcher("/signIn.ftl");
//            System.out.println(requestDispatcher);
//            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = service.signIn(req.getParameter("login"), req.getParameter("password"));
        HttpSession session = req.getSession();
        session.setAttribute("id", user.getId());
        session.setAttribute("role", user.getRole());
        try {
            resp.sendRedirect("profile");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
