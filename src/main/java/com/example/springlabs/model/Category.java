package com.example.springlabs.model;


import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private static long nextId = 0; // temporary solution for id generation
    private long id = nextId++;
    private String name;
    private Set<Category> subCategories;
    private List<Product> products;

    public Category(String name) {
        this.name = name;
    }
}

