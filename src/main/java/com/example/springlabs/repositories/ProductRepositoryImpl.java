package com.example.springlabs.repositories;

import com.example.springlabs.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    private final List<Product> products;

    public ProductRepositoryImpl(ArrayList<Product> products) {
        this.products = products;
    }
    @Override
    public List<Product> getProducts() {
        return products;
    }
    @Override
    public void deleteProductById(long id) {
        products.removeIf(product -> product.getId() == id);
    }
    @Override
    public Product getProductById(long id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
    @Override
    public void updateProduct(Product updatedProduct) {
        for (Product product : products) {
            if (product.getId() == updatedProduct.getId()) {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setCategory(updatedProduct.getCategory());

            }
        }
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }
}


