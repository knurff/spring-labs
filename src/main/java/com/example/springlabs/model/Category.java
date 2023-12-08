package com.example.springlabs.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "categories")
@NamedQuery(name = "Categories.getCategoryById",
        query = "SELECT u FROM categories u WHERE u.id = :id")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class Category implements Comparable<Category> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Category> subCategories = new TreeSet<>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Category(String name, Set<Category> subCategories, List<Product> products) {
        this.name = name;
        this.subCategories = subCategories;
        this.products = products;
    }

    @Override
    public int compareTo(Category o) {
        return this.name.compareTo(o.name);
    }
}