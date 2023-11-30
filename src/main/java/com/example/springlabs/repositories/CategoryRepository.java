package com.example.springlabs.repositories;

import com.example.springlabs.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> getCategories();

    Category addCategory(Category category, Long parentId);


    Optional<Category> updateCategory(Category newCategory);

    void deleteCategory(long id);

    Optional<Category> getCategoryByName(String name);
}
