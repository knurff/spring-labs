package com.example.springlabs.services;

import com.example.springlabs.model.User;
import com.example.springlabs.repositories.UserRepository;
import java.util.List;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void updateUser(Long id, String name, String surname, String email, String password, boolean isAdmin) {
        userRepository.updateUser(id, name, surname, email, password, isAdmin);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }

    @Transactional
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.getUserById(id);
    }
}

