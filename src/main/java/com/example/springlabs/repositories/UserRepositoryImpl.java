package com.example.springlabs.repositories;

import com.example.springlabs.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final List<User> users;

    public UserRepositoryImpl(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void deleteUserById(long id) {
        users.removeIf(user -> user.getId() == id);
    }

    @Override
    public User getUserById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateUser(User updatedUser) {
        for (User user : users) {
            if (user.getId() == updatedUser.getId()) {
                user.setName(updatedUser.getName());
                user.setSurname(updatedUser.getSurname());
                user.setEmail(updatedUser.getEmail());
                user.setPassword(updatedUser.getPassword());
                user.setAdmin(updatedUser.isAdmin());
                break;
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}


