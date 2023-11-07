package com.example.springlabs.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Category implements Comparable<Category> {
    private static long nextId = 10; // temporary solution for id generation
    private long id = nextId++;
    private String name;
    private Set<Category> subCategories = new TreeSet<>();
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

