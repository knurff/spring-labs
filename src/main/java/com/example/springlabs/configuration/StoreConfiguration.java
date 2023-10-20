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
        Category category4 = new Category("Кондиціонери");
        Category category3 = new Category("Холодильники");
        Category category2 = new Category("Велика побутова техніка");
        category2.getSubCategories().add(category4);
        category2.getSubCategories().add(category3);
        Category category1 = new Category("Побутова техніка");
        category1.getSubCategories().add(category2);

        // Створюємо товари
        Product product1 = new Product(1, "BOSCH KGN39VI306", 1000.0, category3);
        category3.getProducts().add(product1);
        Product product2 = new Product(2, "BOSH KGN39VI306", 1000.0, category3);
        category3.getProducts().add(product2);

        // Створюємо список категорій
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(category1);

        return categories;
    }

}
