package dev.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    private ResponseEntity<Users> registerUser(@RequestBody Users user) {
        //generate an apiKey
        user.setApiKey(UUID.randomUUID().toString());
        userRepository.save(user);
        return new ResponseEntity<Users>(user, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    private Iterable<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    private Users getUserById(@PathVariable Integer id) {
        return userRepository.findById(id).get();
    }
    @DeleteMapping("/users/{id}")
    private void deleteUserById(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/validate")
    private ResponseEntity<String> validateUser(@RequestParam String email, @RequestParam String password) {
        /*validate the user email and password against the database
        if the user is found, return the apiKey
        if the user is not found, return an error*/

        Optional<Users> user = userRepository.findByEmailAndPassword(email,password);
        if (user.isPresent()) {
            return new ResponseEntity<String>(user.get().getApiKey(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    };
}
