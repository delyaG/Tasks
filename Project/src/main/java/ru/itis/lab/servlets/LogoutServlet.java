package ru.itis.lab.servlets;

import ru.itis.lab.services.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    ProductService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect("signIn");
    }
}
