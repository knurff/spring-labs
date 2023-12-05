package com.example.springlabs.controllers;

import com.example.springlabs.controllers.dtos.CategoryDto;
import com.example.springlabs.controllers.dtos.mappers.CategoryDtoMapper;
import com.example.springlabs.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

@Tag(name = "Category", description = "Operations related to categories")
@RestController
@RequestMapping(value = "/categories",  produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

  CategoryService categoryService;

  CategoryDtoMapper categoryDtoMapper;

  @Operation(summary = "Get all categories", description = "Returns a list of all categories.", parameters = {
          @Parameter(name = "page", description = "Page number starting from 0"),
          @Parameter(name = "size", description = "Size of page"),
          @Parameter(name = "name", description = "Value for filtering by category name")
  })
  @ApiResponse(responseCode = "200", description = "Successful given categories",
          content = @Content(schema = @Schema(implementation = CategoryDto.class)))
  @GetMapping
  public Collection<CategoryDto> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "0") int size,
                                        @RequestParam(value = "name", defaultValue = "") String name) {
    return categoryDtoMapper.createDtosSet(categoryService.getAllCategories(page, size, name));
  }

  @Operation(summary = "Get a category by name", description = "Returns a category by its name.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successful returned category",
                  content = @Content(schema = @Schema(implementation = CategoryDto.class))),
          @ApiResponse(responseCode = "404", description = "Category not found by name", content = @Content)
  })
  @GetMapping("/**")
  public CategoryDto getCategory(HttpServletRequest request) {
    List<String> urlComponents = getUrlComponents(request);
    return categoryDtoMapper.createDto(categoryService.getCategoryByName(urlComponents));
  }

  @Operation(summary = "Add a new category", description = "Creates a new category.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "A new category created", content = @Content(schema = @Schema(implementation = CategoryDto.class))),
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
          @ApiResponse(responseCode = "201", description = "New subcategory for the specified category created",
                  content = @Content(schema = @Schema(implementation = CategoryDto.class))),
          @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content),
          @ApiResponse(responseCode = "404", description = "Parent category is not found by name", content = @Content)
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
          @ApiResponse(responseCode = "200", description = "Category updated",
                  content = @Content(schema = @Schema(implementation = CategoryDto.class))),
          @ApiResponse(responseCode = "400", description = "Invalid category or request data", content = @Content),
          @ApiResponse(responseCode = "404", description = "Category is not found by id", content = @Content)
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
          @ApiResponse(responseCode = "404", description = "Category is not found by id")})
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCategory(@PathVariable long id) {
    categoryService.deleteCategory(id);
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