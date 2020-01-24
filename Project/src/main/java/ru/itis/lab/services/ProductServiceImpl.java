package ru.itis.lab.services;

import ru.itis.Component;
import ru.itis.lab.models.Product;
import ru.itis.lab.repositories.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService, Component {

    ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
