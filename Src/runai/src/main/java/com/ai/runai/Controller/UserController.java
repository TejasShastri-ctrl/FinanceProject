package com.ai.runai.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.runai.DTO.UserDTO;
import com.ai.runai.Models.User;
import com.ai.runai.Services.UserService;

@CrossOrigin(origins = "http://localhost:3000") // or the port where React is running
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST: Create a new user
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(String name) {
        User user = userService.getUser(name);
        return ResponseEntity.ok(user);
    }

    // @PostMapping("/login")
    // public ResponseEntity<User> login(String email, String password) {
    // User user = userService.getUserProperly(email, password);
    // return ResponseEntity.ok(user);
    // }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestParam String email, @RequestParam String password) {
        UserDTO user = userService.getUserProperly(email, password);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserID(@PathVariable("id") long id) {
        Optional<User> user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
