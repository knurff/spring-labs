package com.example.springlabs.repositories;

import com.example.springlabs.model.Category;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> getCategories();

    void addCategory(Category category);

    Category getCategoryById(long id);

    Optional<Category> updateCategory(long id, Category newCategory,
        Collection<Category> subcategories);

    void deleteCategory(long id, Collection<Category> subcategories);

    Optional<Category> getCategoryByName(String name);
}
