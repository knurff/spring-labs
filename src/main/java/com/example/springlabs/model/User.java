package com.example.springlabs.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private boolean isAdmin;
    private Set<Organization> organizations;
}
