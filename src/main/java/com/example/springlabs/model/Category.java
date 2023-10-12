package com.example.springlabs.model;

import java.util.List;

public class Category {
    public int id;
    public String name;
    public Category parentCategory;

    public Category(int id, String name, Category parentCategory) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {

    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentCategory=" + parentCategory.toString() +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}

