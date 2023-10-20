package com.example.springlabs.model;


import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Comparable<Category> {
    private static long nextId = 0; // temporary solution for id generation
    private long id = nextId++;
    private String name;
    private Set<Category> subCategories = new TreeSet<>();
    private List<Product> products = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Category o) {
        return this.name.compareTo(o.name);
    }
}

