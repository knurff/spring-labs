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

    private static final String SELECT_ALL_SUPER_CATEGORIES = "select id, name, parent_category_id from categories where parent_category_id IS NULL";
    private static final String INSERT = "insert into categories(name, parent_category_id) VALUES (?, ?)";
    private static final String UPDATE = "update categories set name = ? where id = ?";
    private static final String DELETE = "delete from categories where id = ?";
    private static final String SELECT_BY_NAME = "select id, name, parent_category_id from categories where name = ?";
    private static final String SELECT_SUB_CATEGORIES = "select id, name from categories where parent_category_id = ?";
    private static final String SELECT_PRODUCTS = "select id, name, price from products where category_id = ?";

    JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> getCategories() {
        return jdbcTemplate.query(SELECT_ALL_SUPER_CATEGORIES, (rs, rNum) -> extractCategory(rs));
    }

    @Override
    public Category addCategory(Category category, Long parentId) {
        jdbcTemplate.update(INSERT, category.getName(), parentId);
        return getCategoryByName(category.getName()).orElse(null);
    }

    @Override
    public Optional<Category> updateCategory(Category newCategory) {
        jdbcTemplate.update(UPDATE, newCategory.getName(), newCategory.getId());
        return getCategoryByName(newCategory.getName());
    }

    @Override
    public void deleteCategory(long id) {
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return jdbcTemplate.query(SELECT_BY_NAME,
                (ResultSet rs) -> rs.next() ? Optional.of(extractCategory(rs)) : Optional.empty(), name);
    }

    private Set<Category> getCategoriesByParentId(long parentId) {
        return new TreeSet<>(jdbcTemplate.query(SELECT_SUB_CATEGORIES, (rs, rowNum) -> extractCategory(rs), parentId));
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
        return jdbcTemplate.query(SELECT_PRODUCTS,
                (rs, rowNum) -> {
                    Product product = new Product();
                    product.setId(rs.getLong("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    return product;
                }, categoryId);
    }
}
