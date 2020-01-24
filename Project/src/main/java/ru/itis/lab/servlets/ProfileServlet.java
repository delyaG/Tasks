package ru.itis.lab.servlets;

import ru.itis.MyContext;
import ru.itis.MyReflectionContext;
import ru.itis.lab.models.Role;
import ru.itis.lab.models.User;
import ru.itis.lab.services.ProfileService;
import ru.itis.lab.services.ProfileServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private ProfileService service;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        Object rowAttribute = servletContext.getAttribute("context");
//        this.service = applicationContext.getComponent(ProfileService.class, "profileServiceImpl");

        MyContext context = (MyReflectionContext) rowAttribute;
        this.service = context.getComponent(ProfileServiceImpl.class.getName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("id");
        Role role = (Role) session.getAttribute("role");
        User user = service.getUser(userId);
        req.setAttribute("login", user.getLogin());
        req.setAttribute("role", role.toString());
        req.getRequestDispatcher("/profile.ftl").forward(req, resp);
    }
}
