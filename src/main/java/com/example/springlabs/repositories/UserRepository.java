package com.example.springlabs.repositories;

import com.example.springlabs.model.User;
import java.util.List;

public interface UserRepository {

    List<User> getUsers();

    void deleteUserById(long id);

    User getUserById(long id);

    void updateUser(User updatedUser);

    void addUser(User user);
}
