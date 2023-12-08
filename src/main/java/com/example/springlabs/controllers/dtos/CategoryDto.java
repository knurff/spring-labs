package com.example.springlabs.controllers.dtos;

import com.example.springlabs.model.Product;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
  @NotBlank
  private long id;
  @JsonSetter(nulls = Nulls.SKIP)
  @NotBlank
  private String name = "";

  @JsonSetter(nulls = Nulls.SKIP)
  private Set<CategoryDto> subCategories = Collections.emptySet();

  @JsonSetter(nulls = Nulls.SKIP)
  private List<Product> products = Collections.emptyList();
}