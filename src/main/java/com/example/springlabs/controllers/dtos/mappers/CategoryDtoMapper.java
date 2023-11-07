package com.example.springlabs.controllers.dtos.mappers;

import com.example.springlabs.controllers.dtos.CategoryDto;
import com.example.springlabs.model.Category;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoMapper {

  public CategoryDto createDto(Category category) {
    return CategoryDto.builder()
        .id(category.getId())
        .name(category.getName())
        .subCategories(createDtosSet(category.getSubCategories()))
        .products(category.getProducts())
        .build();
  }

  public Set<CategoryDto> createDtosSet(Collection<Category> categories) {
    return categories.stream()
        .map(this::createDto)
        .collect(Collectors.toSet());
  }

  public Category createCategoryFromDto(CategoryDto categoryDto) {
    return new Category(categoryDto.getName(),
        this.createCategorySet(categoryDto.getSubCategories()),
        categoryDto.getProducts());
  }

  public Set<Category> createCategorySet(Collection<CategoryDto> categories) {
    return categories.stream()
        .map(this::createCategoryFromDto)
        .collect(Collectors.toSet());
  }

}
