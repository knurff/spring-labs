package com.example.springlabs.controllers.dtos;

import com.example.springlabs.model.Product;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryDto {
  private long id;
  private String name;
  private Set<CategoryDto> subCategories;
  private List<Product> products;
}
