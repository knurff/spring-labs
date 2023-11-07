package com.example.springlabs.services;

import com.example.springlabs.exceptions.CategoryNotFoundException;
import com.example.springlabs.model.Category;
import com.example.springlabs.repositories.CategoryRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

  CategoryRepository categoryRepository;

  String CATEGORY_NAME_NOT_FOUND = "Category with name: %s doesn't exist!";
  String CATEGORY_ID_NOT_FOUND = "Category with id: %d doesn't exist!";

  public List<Category> getAllCategories() {
    return categoryRepository.getCategories();
  }

  public Category addCategory(Category category) {
    categoryRepository.addCategory(category);
    return category;
  }

  public Category addSubcategory(List<String> categoriesNames, Category categoryFromDto) {
    getSubcategories(categoriesNames).add(categoryFromDto);
    return categoryFromDto;
  }

  public Collection<Category> getSubcategories(List<String> categoriesNames) {
    if (!categoriesNames.isEmpty()) {
      Optional<Category> parentCategory = categoryRepository.getCategoryByName(
          categoriesNames.get(0));
      for (int i = 1; i < categoriesNames.size(); i++) {
        if (parentCategory.isEmpty()) {
          throw new CategoryNotFoundException(
              CATEGORY_NAME_NOT_FOUND.formatted(categoriesNames.get(i - 1)));
        }
        parentCategory = getSubcategoryByName(parentCategory.get(), categoriesNames.get(i));
      }

      return parentCategory.orElseThrow(
              () -> new CategoryNotFoundException(
                  CATEGORY_NAME_NOT_FOUND.formatted(categoriesNames.get(categoriesNames.size() - 1))))
          .getSubCategories();
    }

    return Collections.emptyList();
  }

  private Optional<Category> getSubcategoryByName(Category category, String name) {
    return category.getSubCategories().stream()
        .filter(subCategory -> subCategory.getName().equals(name))
        .findFirst();
  }

  public Category updateCategory(List<String> categoriesNames, long id,
      Category categoryFromDto) {
      return categoryRepository.updateCategory(id, categoryFromDto, getSubcategories(categoriesNames))
          .orElseThrow(() -> new CategoryNotFoundException(CATEGORY_ID_NOT_FOUND.formatted(id)));
  }

  public void deleteCategory(List<String> categoriesNames, long id) {
    categoryRepository.deleteCategory(id, getSubcategories(categoriesNames));
  }
}

