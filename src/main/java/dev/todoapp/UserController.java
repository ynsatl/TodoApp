package dev.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    private ResponseEntity<User> registerUser(@RequestBody User user) {
        var savedUser = userRepository.save(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    private Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
