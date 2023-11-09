package com.example.springlabs.controllers;

import com.example.springlabs.controllers.dtos.CategoryDto;
import com.example.springlabs.controllers.dtos.mappers.CategoryDtoMapper;
import com.example.springlabs.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Category", description = "The Category API")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

  CategoryService categoryService;

  CategoryDtoMapper categoryDtoMapper;

  @Operation(summary = "Get all categories", description = "Returns a list of all categories.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successful given categories", content = {
                  @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))}),
          @ApiResponse(responseCode = "404", description = "Categories are not found", content = @Content)
  })
  @GetMapping
  public Collection<CategoryDto> getAll() {
    return categoryDtoMapper.createDtosSet(categoryService.getAllCategories());
  }

  @Operation(summary = "Get a category by name", description = "Returns a category by its name.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successful returned category", content = {
                  @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))}),
          @ApiResponse(responseCode = "400", description = "Invalid category or request data", content = @Content),
          @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
  })
  @GetMapping("/**")
  public CategoryDto getCategory(HttpServletRequest request) {
    List<String> urlComponents = getUrlComponents(request);
    return categoryDtoMapper.createDto(categoryService.getCategoryByName(urlComponents));
  }

  @Operation(summary = "Add a new category", description = "Creates a new category.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "A new category created", content = {
                  @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))}),
          @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
    return categoryDtoMapper.createDto(
        categoryService.addCategory(categoryDtoMapper.createCategoryFromDto(categoryDto)));
  }

  @Operation(summary = "Add a subcategory to a category", description = "Creates a new subcategory for the specified category.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "New subcategory for the specified category created", content = {
                  @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))}),
          @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content),
          @ApiResponse(responseCode = "404", description = "Parent category is not found", content = @Content)
  })
  @PostMapping("/**")
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryDto addSubcategory(HttpServletRequest request,
      @RequestBody CategoryDto categoryDto) {
    List<String> urlComponents = getUrlComponents(request);
    return categoryDtoMapper.createDto(
        categoryService.addSubcategory(urlComponents,
            categoryDtoMapper.createCategoryFromDto(categoryDto)));
  }

  @Operation(summary = "Update a category", description = "Updates an existing category.")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Category updated", content = {
                  @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))}),
          @ApiResponse(responseCode = "400", description = "Invalid category or request data", content = @Content),
          @ApiResponse(responseCode = "404", description = "Category is not found", content = @Content)
  })
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

  @Operation(summary = "Delete a category", description = "Deletes an existing category.")
  @ApiResponses({
          @ApiResponse(responseCode = "204", description = "Category was successfully deleted"),
          @ApiResponse(responseCode = "404", description = "Category is not found")})
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