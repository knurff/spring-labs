package com.example.springlabs.repositories;

import com.example.springlabs.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getProducts();

    void deleteProductById(long id);

    Product getProductById(long id);

    void updateProduct(Product updatedProduct);

    void addProduct(Product product);
}
