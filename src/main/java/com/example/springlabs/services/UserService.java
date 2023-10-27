package com.example.springlabs.services;

import com.example.springlabs.model.User;
import com.example.springlabs.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(long id) {
        return userRepository.getUserById(id);
    }

    public List<User> deleteUserById(long id) {
        userRepository.deleteUserById(id);
        return userRepository.getUsers();
    }

    public List<User> updateUser(User updatedUser) {
         userRepository.updateUser(updatedUser);
         return userRepository.getUsers();
    }

    public List<User> addUser(User user) {
        userRepository.addUser(user);
        return userRepository.getUsers();
    }
}

