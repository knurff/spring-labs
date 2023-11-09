package com.example.springlabs.controllers;

import com.example.springlabs.controllers.dtos.CategoryDto;
import com.example.springlabs.controllers.dtos.mappers.CategoryDtoMapper;
import com.example.springlabs.services.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

  CategoryService categoryService;

  CategoryDtoMapper categoryDtoMapper;

  @GetMapping
  public Collection<CategoryDto> getAll() {
    return categoryDtoMapper.createDtosSet(categoryService.getAllCategories());
  }

  @GetMapping("/**")
  public CategoryDto getCategory(HttpServletRequest request) {
    List<String> urlComponents = getUrlComponents(request);
    return categoryDtoMapper.createDto(categoryService.getCategoryByName(urlComponents));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
    return categoryDtoMapper.createDto(
        categoryService.addCategory(categoryDtoMapper.createCategoryFromDto(categoryDto)));
  }

  @PostMapping("/**")
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryDto addSubcategory(HttpServletRequest request,
      @RequestBody CategoryDto categoryDto) {
    List<String> urlComponents = getUrlComponents(request);
    return categoryDtoMapper.createDto(
        categoryService.addSubcategory(urlComponents,
            categoryDtoMapper.createCategoryFromDto(categoryDto)));
  }

  @PutMapping("/**")
  @ResponseStatus(HttpStatus.OK)
  public CategoryDto updateCategory(HttpServletRequest request,
      @RequestBody CategoryDto categoryDto) {
    List<String> urlComponents = new ArrayList<>(getUrlComponents(request));
    String id = urlComponents.remove(urlComponents.size() - 1);
    return categoryDtoMapper.createDto(
        categoryService.updateCategory(urlComponents, id,
            categoryDtoMapper.createCategoryFromDto(categoryDto)));
  }

  @DeleteMapping("/**")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCategory(HttpServletRequest request) {
    List<String> urlComponents = new ArrayList<>(getUrlComponents(request));
    String id = urlComponents.remove(urlComponents.size() - 1);
        categoryService.deleteCategory(urlComponents, id);
  }

  private List<String> getUrlComponents(HttpServletRequest request) {
    return Arrays.stream(new AntPathMatcher().extractPathWithinPattern(
                request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString(),
                URLDecoder.decode(
                    request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString(),
                    Charset.defaultCharset()))
            .split("/"))
        .toList();
  }
}
