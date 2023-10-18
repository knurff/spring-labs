package com.example.springlabs.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private long id;
    private String name;
    private double price;
    private Organization seller;
    private Category category;
}


