package com.ai.runai.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ai.runai.Models.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByEmail(String email); // Add method to find by email

    // @Query(value = "SELECT * FROM f_user WHERE email = :email", nativeQuery = true)
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);
}
