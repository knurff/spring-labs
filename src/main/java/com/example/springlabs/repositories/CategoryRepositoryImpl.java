package com.example.springlabs.repositories;

import com.example.springlabs.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private final ArrayList<Category> categories;

    public CategoryRepositoryImpl(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public ArrayList<Category> getCategories() {
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        categories.add(category);
    }

    @Override
    public Category getCategoryById(int id) {
        return categories.stream()
                .filter(category -> category.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<Category> updateCategories(Category updatedCategory) {
        for (Category category : categories) {
            if (category.getId() == updatedCategory.getId()) {
                category.setName(updatedCategory.getName());
                category.setParentCategory(updatedCategory.getParentCategory());
                return categories;
            }
        }
        return categories;
    }
    @Override
    public void updateCategory(int id, String newName, Category newParentCategory) {
        for (Category category : categories) {
            if (category.getId() == id) {
                category.setName(newName);
                category.setParentCategory(newParentCategory);
            }
        }
    }
    @Override
    public void deleteCategoryById(int id) {
        categories.removeIf(category -> category.getId() == id);
    }
}
