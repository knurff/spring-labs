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
      return categoryRepository.addCategory(category, null);
  }

    public Category addCategory(Category category, List<String> categoriesNames) {
      return categoryRepository.addCategory(category, getCategoryByName(categoriesNames).getId());
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
      getCategoryByName(categoriesNames);
      categoryFromDto.setId(saveParseId(stringId));
      return categoryRepository.updateCategory(categoryFromDto)
              .orElseThrow(() -> new CategoryNotFoundException(CATEGORY_ID_NOT_FOUND.formatted(stringId)));
    }

  public void deleteCategory(long id) {
      categoryRepository.deleteCategory(id);
  }

  private long saveParseId(String s) {
      try {
          return Long.parseLong(s);
      } catch (NumberFormatException e) {
          throw new CategoryNotFoundException(CATEGORY_ID_NOT_FOUND.formatted(s), e);
      }
  }

    public Collection<Category> getAllCategories(int page, int size, String name) {
        Collection<Category> categories = getAllCategories().stream().filter(c -> c.getName().contains(name)).toList();
        if (size > 0 && page >= 0)
            return categories.stream().skip((long) page * size).limit(size).toList();
        else
            return categories;
    }
}

