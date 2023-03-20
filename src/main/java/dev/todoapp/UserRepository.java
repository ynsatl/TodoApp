package dev.todoapp;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Integer> {
    Optional<Users> findByEmailAndPassword(String email, String password);
    Optional<Users> findByApiKey(String apiKey);
}
