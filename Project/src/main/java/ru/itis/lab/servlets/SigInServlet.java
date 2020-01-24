package ru.itis.lab.servlets;

import ru.itis.MyContext;
import ru.itis.MyReflectionContext;
import ru.itis.lab.models.User;
import ru.itis.lab.services.SignInService;
import ru.itis.lab.services.SignInServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SigInServlet extends HttpServlet {

    private SignInService service;

    @Override
    public void init(ServletConfig config)  {
        ServletContext servletContext = config.getServletContext();
        Object rawAttribute = servletContext.getAttribute("context");
//        this.service = applicationContext.getComponent(SignInService.class, "signInServiceImpl");
        MyContext context = (MyReflectionContext) rawAttribute;
        this.service = context.getComponent(SignInServiceImpl.class.getName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signIn.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = service.signIn(req.getParameter("login"), req.getParameter("password"));
        HttpSession session = req.getSession();
        session.setAttribute("id", user.getId());
        session.setAttribute("role", user.getRole());
        resp.sendRedirect("profile");
    }
}
