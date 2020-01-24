package ru.itis.lab.servlets;

import ru.itis.MyContext;
import ru.itis.MyReflectionContext;
import ru.itis.lab.services.SignUpService;
import ru.itis.lab.services.SignUpServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SignUpService service;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        Object rawAttribute = servletContext.getAttribute("context");
//        this.service = applicationContext.getComponent(SignUpService.class, "signUpServiceImpl");
        MyContext context = (MyReflectionContext) rawAttribute;
        this.service = context.getComponent(SignUpServiceImpl.class.getName());
        System.out.println();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signUp.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        service.signUp(req.getParameter("login"), req.getParameter("password"));
        resp.sendRedirect("signIn");
    }
}
