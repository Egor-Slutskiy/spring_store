package com.example.store.controller;

import com.example.store.entity.Model;
import com.example.store.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ModelController {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @PostMapping(value = "/api/model")
    public ResponseEntity<?> create(@RequestBody Model model) {
        modelRepository.save(model);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/model")
    public ResponseEntity<?> findAll() {
        final List<Model> modelList = modelRepository.findAll();

        if(modelList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(modelList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/api/model/{id}")
    public ResponseEntity<?> find(@PathVariable(name = "id") Long id) {
        final Optional<Model> model = modelRepository.findById(id);

        if(model.isPresent()){
            return new ResponseEntity<>(model, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/api/model/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        modelRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
