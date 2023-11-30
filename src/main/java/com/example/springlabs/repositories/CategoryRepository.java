package com.example.springlabs.repositories;

import com.example.springlabs.model.Category;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> getCategories();

    Category addCategory(Category category, Long parentId);


    Optional<Category> updateCategory(long id, Category newCategory,
        Collection<Category> subcategories);

    void deleteCategory(long id);

    Optional<Category> getCategoryByName(String name);
}
