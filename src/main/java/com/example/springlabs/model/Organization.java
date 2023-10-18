package com.example.springlabs.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Organization {
    private long id;
    private String name;
    private User creator;
    private List<Product> products;
}
