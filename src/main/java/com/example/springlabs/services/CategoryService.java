package com.example.springlabs.services;

import com.example.springlabs.model.Category;
import com.example.springlabs.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ArrayList<Category> getAllCategories() {
        return categoryRepository.getCategories();
    }

    public Category getCategoryById(int id) {
        return categoryRepository.getCategoryById(id);
    }

    public List<Category> deleteCategoryById(int id) {
        categoryRepository.deleteCategoryById(id);
        return categoryRepository.getCategories();
    }


    public List<Category> updateCategory(int id, String newName, Category newParentCategory){
        categoryRepository.updateCategory(id, newName, newParentCategory);
        return categoryRepository.getCategories();
    }

    public List<Category> addCategory(Category category) {
        categoryRepository.addCategory(category);
        return categoryRepository.getCategories();
    }
}

