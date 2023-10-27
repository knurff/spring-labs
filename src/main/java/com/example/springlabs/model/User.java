package com.example.springlabs.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private boolean isAdmin;
}
