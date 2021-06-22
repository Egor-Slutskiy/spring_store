package com.example.store.controller;

import com.example.store.entity.UserProfile;
import com.example.store.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserProfileController {
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileController(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @PostMapping(value = "/api/userProfile")
    public ResponseEntity<?> create(@RequestBody UserProfile userProfile) {
        userProfileRepository.save(userProfile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/userProfile")
    public ResponseEntity<?> findAll() {
        final List<UserProfile> userProfileList = userProfileRepository.findAll();

        if(userProfileList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(userProfileList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/api/userProfile/{id}")
    public ResponseEntity<?> find(@PathVariable(name = "id") Long id) {
        final Optional<UserProfile> userProfile = userProfileRepository.findById(id);

        if(userProfile.isPresent()){
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/api/userProfile/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        userProfileRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
