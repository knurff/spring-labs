package com.example.springlabs.services;

import com.example.springlabs.exception.CategoryNotFoundException;
import com.example.springlabs.model.Category;
import com.example.springlabs.repositories.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

  CategoryRepository categoryRepository;

  String CATEGORY_NAME_NOT_FOUND = "Category with name: %s doesn't exist!";
  String CATEGORY_ID_NOT_FOUND = "Category with id: %s doesn't exist!";

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
        return getCategoryByName(categoriesNames).getSubCategories();
    }

  private Optional<Category> getSubcategoryByName(Category category, String name) {
    return category.getSubCategories().stream()
        .filter(subCategory -> subCategory.getName().equals(name))
        .findFirst();
  }

    public Category getCategoryByName(List<String> categoriesNamesPath) {
        Optional<Category> parentCategory = categoryRepository.getCategoryByName(categoriesNamesPath.get(0));
        for (int i = 1; i < categoriesNamesPath.size(); i++) {
            if (parentCategory.isEmpty()) {
                throw new CategoryNotFoundException(
                        CATEGORY_NAME_NOT_FOUND.formatted(categoriesNamesPath.get(i - 1)));
            }
            parentCategory = getSubcategoryByName(parentCategory.get(), categoriesNamesPath.get(i));
        }

        return parentCategory.orElseThrow(
                () -> new CategoryNotFoundException(
                        CATEGORY_NAME_NOT_FOUND.formatted(categoriesNamesPath.get(categoriesNamesPath.size() - 1))));
    }

    public Category updateCategory(List<String> categoriesNames, String stringId,
                                   Category categoryFromDto) {
        try {
            long id = Long.parseLong(stringId);
            return categoryRepository.updateCategory(id, categoryFromDto, getSubcategories(categoriesNames))
                    .orElseThrow(() -> new CategoryNotFoundException(CATEGORY_ID_NOT_FOUND.formatted(stringId)));
        } catch (NumberFormatException e) {
            throw new CategoryNotFoundException(CATEGORY_ID_NOT_FOUND.formatted(stringId), e);
        }
    }

  public void deleteCategory(List<String> categoriesNames, String stringId) {
      try {
          long id = Long.parseLong(stringId);
          categoryRepository.deleteCategory(id, getSubcategories(categoriesNames));
      } catch (NumberFormatException e) {
          throw new CategoryNotFoundException(CATEGORY_ID_NOT_FOUND.formatted(stringId), e);
      }
  }
}

