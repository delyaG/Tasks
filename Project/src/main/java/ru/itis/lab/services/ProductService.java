package ru.itis.lab.services;

import ru.itis.lab.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    void addProduct(Product product);
}
