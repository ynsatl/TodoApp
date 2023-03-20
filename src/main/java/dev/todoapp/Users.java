package dev.todoapp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = true)
    private String password;

    @OneToMany
    @JoinColumn(name = "userId")
    private Set<Todo> todos;

    @JsonIgnore
    @Column(nullable = false)
    private String apiKey;

}
