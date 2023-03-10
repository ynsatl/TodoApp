package dev.todoapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/todo")
    public ResponseEntity<Todo> get(@RequestParam(value = "id")Integer id) {

        //get todo from db by id

        Optional<Todo> todoFromDb = todoRepository.findById(id);

        return todoFromDb.map(todo -> new ResponseEntity<>(todo, HttpStatus.OK)).orElseGet(() -> new ResponseEntity("ID "+id+" NOT FOUND", HttpStatus.NOT_FOUND));
    }

    @PostMapping("/todo")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
        //save new todo to the database
        todoRepository.save(newTodo);

        return new ResponseEntity<Todo>(newTodo, HttpStatus.CREATED);
    }

    @DeleteMapping("/todo")
    public ResponseEntity deleteTodo(@RequestParam(value = "id") Integer id) {
        //delete todo from the database with id

        if (todoRepository.findById(id).isPresent()) {
            todoRepository.deleteById(id);
            return new ResponseEntity("DELETE SUCCESSFUL",HttpStatus.OK);
        }
        return new ResponseEntity("TODO NOT FOUND",HttpStatus.NOT_FOUND);

    }

    @PutMapping("/todo")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo editTodo) {
        //update todo in the database with id


        if (todoRepository.findById(editTodo.getId()).isPresent()) {
            todoRepository.save(editTodo);
            return new ResponseEntity("UPDATE SUCCESSFUL",HttpStatus.OK);
        }
        return new ResponseEntity("TODO NOT FOUND",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/todo/all")
    public ResponseEntity<Iterable<Todo>> getAllByUserId(@RequestHeader("apiKey") String apiKey) {
        //get all todos with user apiKey from the database
        System.out.println(apiKey);
        var user = userRepository.findByApiKey(apiKey);
        Iterable<Todo> all = todoRepository.findAllByUserId(user.get().getId());

        return new ResponseEntity<Iterable<Todo>>(all, HttpStatus.OK);
    }

    @PatchMapping("/todo/changeStatus")
    public ResponseEntity<Todo> updateTodoDone(@RequestParam(value = "id") Integer id) {
        //update todo in the database with id


        if (todoRepository.findById(id).isPresent()) {
            Todo todo = todoRepository.findById(id).get();
            todo.setDone(!todo.getIsDone());
            todoRepository.save(todo);
            return new ResponseEntity<Todo>(todo,HttpStatus.OK);
        }
        return new ResponseEntity("TODO NOT FOUND",HttpStatus.NOT_FOUND);
    }
}
