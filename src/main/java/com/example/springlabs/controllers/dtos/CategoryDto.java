package com.example.springlabs.controllers.dtos;

import com.example.springlabs.model.Product;
import java.util.List;
import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryDto {
  @NotBlank
  private long id;
  @NotBlank
  private String name;
  private Set<CategoryDto> subCategories;
  private List<Product> products;
}