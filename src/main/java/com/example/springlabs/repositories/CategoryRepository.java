package com.example.springlabs.repositories;

import com.example.springlabs.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    //find by id - @NamedEntity
    @Query(nativeQuery = true, name = "Categories.getCategoryById")
    Category getCategoryById(@Param("id") Long id);

    Category findById(long id);

    // Delete - @Query
    @Modifying
    @Transactional
    @Query("DELETE FROM categories u WHERE u.id = :id")
    void deleteCategory(@Param("id") Long id);

    //getCategoryByName
    @Query(value = "SELECT * FROM categories c WHERE c.name = :name", nativeQuery = true)
    Optional<Category> getCategoryByName(@Param("name") String name);
}
