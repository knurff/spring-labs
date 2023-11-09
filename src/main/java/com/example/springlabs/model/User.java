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
public class User {
    private static long nextId = 10; // temporary solution for id generation
    private long id = nextId++;
    private String name;
    private String surname;
    private String email;
    private String password;
    private boolean isAdmin;
}
