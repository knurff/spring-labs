package com.example.springlabs.repositories;

import com.example.springlabs.model.Category;

import java.util.ArrayList;

public interface CategoryRepository {
    ArrayList<Category> getCategories();

    void addCategory(Category category);

    Category getCategoryById(long id);

    ArrayList<Category> updateCategories(Category updatedCategory);

    void updateCategory(long id, String newName, Category newParentCategory);

    void deleteCategoryById(long id);
}
