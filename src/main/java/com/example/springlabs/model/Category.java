package com.example.springlabs.model;


import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Category {
    private int id;
    private String name;
    private Category parentCategory;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

