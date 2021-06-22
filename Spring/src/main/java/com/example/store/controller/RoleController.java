package com.example.store.controller;

import com.example.store.entity.Role;
import com.example.store.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class RoleController {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping(value = "/api/role")
    public ResponseEntity<?> create(@RequestBody Role role) {
        roleRepository.save(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/role")
    public ResponseEntity<?> findAll() {
        final List<Role> roleList = roleRepository.findAll();

        if(roleList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(roleList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/api/role/{id}")
    public ResponseEntity<?> find(@PathVariable(name = "id") Long id) {
        final Optional<Role> role = roleRepository.findById(id);

        if(role.isPresent()){
            return new ResponseEntity<>(role, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/api/role/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final Optional<Role> role = roleRepository.findById(id);
        roleRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
