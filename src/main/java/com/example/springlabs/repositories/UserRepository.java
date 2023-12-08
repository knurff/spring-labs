package com.example.springlabs.repositories;

import com.example.springlabs.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //find by id - @NamedEntity
    @Query(nativeQuery = true, name = "Users.getUserById")
    User getUserById(@Param("id") Long id);

    // read all - jpa
    List<User> findAll();

    // Update - @Query
    @Modifying
    @Transactional
    @Query("UPDATE users u SET u.name = :name, u.surname = :surname, u.email = :email, u.password = :password, u.isAdmin = :isAdmin WHERE u.id = :id")
    void updateUser(@Param("id") Long id, @Param("name") String name, @Param("surname") String surname, @Param("email") String email, @Param("password") String password, @Param("isAdmin") boolean isAdmin);

    // Delete - @Query
    @Modifying
    @Transactional
    @Query("DELETE FROM users u WHERE u.id = :id")
    void deleteUserById(@Param("id") Long id);
}
