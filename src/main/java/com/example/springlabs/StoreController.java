package com.example.springlabs;

import com.example.springlabs.model.Category;
import com.example.springlabs.model.Product;
import com.example.springlabs.services.CategoryService;
import com.example.springlabs.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Controller
@AllArgsConstructor
public class StoreController {

    private ProductService productService;

    private CategoryService categoryService;

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.getAllProducts();
        ArrayList<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
//        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/addCategory")
    public String newCategory(Model model) {
        ArrayList<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "addCategory";
    }

    @PostMapping("/createCategory")
    public String createCategory(
            @RequestParam("categoryName") String name,
            @RequestParam("categorySelect") String parentCategoryIdSt,
            Model model
    ) {
        if (parentCategoryIdSt.isEmpty()) {
            Category category = new Category(name);
            model.addAttribute("categories", categoryService.addCategory(category));
        } else {
            int parentCategoryId = Integer.parseInt(parentCategoryIdSt);
            Category parentCategory = categoryService.getCategoryById(parentCategoryId);
            Category category = new Category();
            parentCategory.getSubCategories().add(category);
            category.setName(name);
            category.setSubCategories(new LinkedHashSet<>());
            category.setProducts(new ArrayList<>());
            model.addAttribute("categories", categoryService.addCategory(category));
        }

        return "allCategories";
//        return "/";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(
            @RequestParam("categoryId") String idSt,
            @RequestParam("categoryName") String name,
            @RequestParam("parentCategoryId") String parentCategoryIdSt,
            Model model
    ) {
        int id = Integer.parseInt(idSt);
        int parentCategoryId = Integer.parseInt(parentCategoryIdSt);
//        Category parentCategory = categoryService.getCategoryById(parentCategoryId);
        ArrayList<Category> categories = categoryService.getAllCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("categoryId", id);
        model.addAttribute("categoryName", name);
        model.addAttribute("parentCategoryId", parentCategoryId);

//        model.addAttribute("parentCategory", parentCategory);
        return "updateCategory";
    }

    @PostMapping("/pageOfAll")
    public String deleteCreateAuthor(
            @RequestParam("categoryId") String idSt,
            @RequestParam("categoryName") String name,
            @RequestParam("parentCategoryId") String parentCategoryIdSt,
            Model model
    ) {
        int id = Integer.parseInt(idSt);
        int parentCategoryId = Integer.parseInt(parentCategoryIdSt);
        Category parentCategory = categoryService.getCategoryById(parentCategoryId);

        model.addAttribute("categories", categoryService.updateCategory(id, name, parentCategory));
        return "/allCategories";
    }

    @PostMapping("/deleteCategory")
    public String deleteAuthor(
            @RequestParam("categoryId") String idSt,
            Model model
    ) {
        int id = Integer.parseInt(idSt);
        model.addAttribute("categories", categoryService.deleteCategoryById(id));
        return "/allCategories";
    }
}

