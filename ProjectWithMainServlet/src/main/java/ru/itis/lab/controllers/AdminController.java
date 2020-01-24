package ru.itis.lab.controllers;

import ru.itis.Controller;
import ru.itis.Mapping;
import ru.itis.lab.models.Product;
import ru.itis.lab.services.ProductService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Mapping("/admin")
public class AdminController implements Controller {

    ProductService service;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ServletContext servletContext) {
        String method = httpServletRequest.getMethod();
        if (method.equals("GET")) {
            doGet(httpServletRequest, httpServletResponse);
        } else if (method.equals("POST")) {
            doPost(httpServletRequest, httpServletResponse);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/adminPanel.ftl").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Product product = Product.builder()
                .name(req.getParameter("name"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
        service.addProduct(product);
        try {
            resp.sendRedirect("shop");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
