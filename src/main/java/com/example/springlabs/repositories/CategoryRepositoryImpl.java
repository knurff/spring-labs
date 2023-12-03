package com.example.springlabs.repositories;

import com.example.springlabs.model.Category;
import com.example.springlabs.model.Product;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryRepositoryImpl implements CategoryRepository {

    JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> getCategories() {
        return jdbcTemplate.query("select id, name, parent_category_id from categories where parent_category_id IS NULL",
                (rs, rNum) -> extractCategory(rs));
    }

    @Override
    public Category addCategory(Category category, Long parentId) {
        jdbcTemplate.update("insert into categories(name, parent_category_id) VALUES (?, ?)", category.getName(), parentId);
        return getCategoryByName(category.getName()).orElse(null);
    }

    @Override
    public Optional<Category> updateCategory(Category newCategory) {
        jdbcTemplate.update("update categories set name = ? where id = ?", newCategory.getName(), newCategory.getId());
        return getCategoryByName(newCategory.getName());
    }

    @Override
    public void deleteCategory(long id) {
        jdbcTemplate.update("delete from categories where id = ?", id);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return jdbcTemplate.query("select id, name, parent_category_id from categories where name = ?",
                (ResultSet rs) -> !rs.next() ? Optional.empty() : Optional.of(extractCategory(rs)), name);
    }

    private Set<Category> getCategoriesByParentId(long parentId) {
        return new TreeSet<>(jdbcTemplate.query("select id, name from categories where parent_category_id = ?",
                (rs, rowNum) -> extractCategory(rs), parentId));
    }

    private Category extractCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        long id = rs.getLong("id");
        category.setId(id);
        category.setName(rs.getString("name"));
        category.setSubCategories(getCategoriesByParentId(rs.getLong("id")));
        category.setProducts(getProductsByCategoryId(id));
        return category;
    }

    private List<Product> getProductsByCategoryId(long categoryId) {
        return jdbcTemplate.query("select id, name, price from products where category_id = ?",
                (rs, rowNum) -> {
                    Product product = new Product();
                    product.setId(rs.getLong("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    return product;
                }, categoryId);
    }
}
