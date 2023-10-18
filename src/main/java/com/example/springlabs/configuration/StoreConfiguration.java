package com.example.springlabs.configuration;


import com.example.springlabs.model.Category;
import com.example.springlabs.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class StoreConfiguration {
    @Bean
    ArrayList<Category> categories() {
        // Створюємо категорії
        Category category1 = new Category(1, "Побутова техніка");
        Category category2 = new Category(2, "Велика побутова техніка", category1, new ArrayList<>());
        Category category3 = new Category(3, "Холодильники", category2, new ArrayList<>());

        // Створюємо товари
        Product product1 = new Product(1, "BOSCH KGN39VI306", 1000.0, null, category3);
        category3.getProducts().add(product1);
        Product product2 = new Product(2, "BOSH KGN39VI306", 1000.0, null, category3);
        category3.getProducts().add(product2);

        // Створюємо список категорій
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        return categories;
    }

}
