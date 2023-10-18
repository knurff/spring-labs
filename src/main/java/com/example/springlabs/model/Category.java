package com.example.springlabs.model;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private long id;
    private String name;
    private Category parentCategory;
    private List<Product> products;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

