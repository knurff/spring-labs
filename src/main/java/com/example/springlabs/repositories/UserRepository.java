package com.example.springlabs.repositories;

import com.example.springlabs.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getUsers();

    void deleteUserById(int id);

    User getUserById(int id);

    void updateUser(User updatedUser);

    void addUser(User user);
}
