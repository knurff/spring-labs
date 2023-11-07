package com.example.springlabs.repositories;

import com.example.springlabs.model.Category;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryRepositoryImpl implements CategoryRepository {

  List<Category> categories;

  @Override
  public List<Category> getCategories() {
    return categories;
  }

  @Override
  public void addCategory(Category category) {
    categories.add(category);
  }

  @Override
  public Category getCategoryById(long id) {
    return categories.stream()
        .filter(category -> category.getId() == id)
        .findFirst()
        .orElse(null);
  }

  @Override
  public Optional<Category> updateCategory(long id, Category newCategory,
      Collection<Category> subcategories) {
    Collection<? extends Category> categoriesToIterate =
        subcategories.isEmpty() ? categories : subcategories;

    for (Category category : categoriesToIterate) {
      if (category.getId() == id) {
        category.setName(newCategory.getName());
        category.setSubCategories(newCategory.getSubCategories());
        category.setProducts(newCategory.getProducts());
        return Optional.of(category);
      }
    }
    return Optional.empty();
  }

  @Override
  public void deleteCategory(long id, Collection<Category> subcategories) {
    Collection<? extends Category> categoriesToIterate =
        subcategories.isEmpty() ? categories : subcategories;
    categoriesToIterate.removeIf(category -> category.getId() == id);
  }

  @Override
  public Optional<Category> getCategoryByName(String name) {
    return categories.stream()
        .filter(category -> category.getName().equals(name))
        .findFirst();
  }
}
