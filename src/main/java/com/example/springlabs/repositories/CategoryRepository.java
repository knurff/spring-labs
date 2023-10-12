package com.example.springlabs.repositories;

import com.example.springlabs.model.Category;

import java.util.ArrayList;

public interface CategoryRepository {
    ArrayList<Category> getCategories();

    void addCategory(Category category);

    Category getCategoryById(int id);

    ArrayList<Category> updateCategories(Category updatedCategory);

    void updateCategory(int id, String newName, Category newParentCategory);

    void deleteCategoryById(int id);
}
