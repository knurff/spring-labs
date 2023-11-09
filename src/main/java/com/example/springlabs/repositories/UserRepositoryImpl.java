package com.example.springlabs.repositories;

import com.example.springlabs.model.User;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRepositoryImpl implements UserRepository{
    List<User> users;

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


