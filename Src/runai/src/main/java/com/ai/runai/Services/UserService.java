package com.ai.runai.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.runai.DTO.UserDTO;
import com.ai.runai.Models.User;
import com.ai.runai.Repos.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    public User createUser(User user) {
        // Check if user with the same email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists!"); // Or use a custom exception
        }
        return userRepository.save(user); // Save the new user
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    // New method to get user by email and password //! USED FOR LOGGING IN(NEEDS
    // WORK(UPDATE INTO JWT))
    // public User getUserProperly(String email, String password) {
    // User user = userRepository.findUserByEmail(email);
    // System.out.println(user);
    // System.out.println(email);
    // if(user==null || !user.getPassword().equals(password)) {
    // throw new RuntimeException("Invalid email or password!");
    // }
    // return user;
    // }

    public UserDTO getUserProperly(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        System.out.println(user);
        System.out.println(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid email or password!");
        }
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    public User getUser(String name) {
        return userRepository.findByName(name);
    }
}
