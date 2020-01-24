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
import java.util.List;

@Mapping("/shop")
public class ShopController implements Controller {

    ProductService service;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ServletContext servletContext) {
        String method = httpServletRequest.getMethod();
        if (method.equals("GET")) {
            doGet(httpServletRequest, httpServletResponse);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> productList = service.getProducts();
        req.setAttribute("products", productList);
        System.out.println(productList.toString());
        try {
            req.getRequestDispatcher("/shop.ftl").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
