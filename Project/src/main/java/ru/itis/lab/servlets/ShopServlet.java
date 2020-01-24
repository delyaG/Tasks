package ru.itis.lab.servlets;

import ru.itis.MyContext;
import ru.itis.MyReflectionContext;
import ru.itis.lab.models.Product;
import ru.itis.lab.services.ProductService;
import ru.itis.lab.services.ProductServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {

    private ProductService service;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        Object rawAttribute = servletContext.getAttribute("context");
//        this.service = applicationContext.getComponent(ProductService.class, "productServiceImpl");

        MyContext context = (MyReflectionContext) rawAttribute;
        this.service = context.getComponent(ProductServiceImpl.class.getName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = service.getProducts();
        req.setAttribute("products", productList);
        System.out.println(productList.toString());
        req.getRequestDispatcher("/shop.ftl").forward(req, resp);
    }
}
