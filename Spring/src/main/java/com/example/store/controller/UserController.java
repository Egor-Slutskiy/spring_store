package com.example.store.controller;

import com.example.store.entity.User;
import com.example.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/api/user")
    public ResponseEntity<?> create(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/user")
    public ResponseEntity<?> findAll() {
        final List<User> userList = userRepository.findAll();

        if(userList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/api/user/{id}")
    public ResponseEntity<?> find(@PathVariable(name = "id") Long id) {
        final Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/api/user/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final Optional<User> user = userRepository.findById(id);
        userRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
