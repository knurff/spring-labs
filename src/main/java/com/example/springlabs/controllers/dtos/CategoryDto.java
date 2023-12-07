package com.example.springlabs.controllers.dtos;

import com.example.springlabs.model.Product;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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