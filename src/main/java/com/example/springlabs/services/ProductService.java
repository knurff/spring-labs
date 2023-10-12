package com.example.springlabs.services;

import com.example.springlabs.model.Product;
import com.example.springlabs.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }

    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    public List<Product> deleteProductById(int id) {
        productRepository.deleteProductById(id);
        return productRepository.getProducts();
    }

    public List<Product> updateProduct(Product updatedProduct) {
         productRepository.updateProduct(updatedProduct);
         return productRepository.getProducts();
    }

    public List<Product> addProduct(Product product) {
        productRepository.addProduct(product);
        return productRepository.getProducts();
    }
}

