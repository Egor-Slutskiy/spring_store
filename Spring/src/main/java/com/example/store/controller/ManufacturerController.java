package com.example.store.controller;

import com.example.store.entity.Manufacturer;
import com.example.store.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ManufacturerController {
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerController(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @PostMapping(value = "/api/manufacturer")
    public ResponseEntity<?> create(@RequestBody Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/manufacturer")
    public ResponseEntity<?> findAll() {
        final List<Manufacturer> manufacturerList = manufacturerRepository.findAll();

        if(manufacturerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(manufacturerList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/api/manufacturer/{id}")
    public ResponseEntity<?> find(@PathVariable(name = "id") Long id) {
        final Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);

        if(manufacturer.isPresent()){
            return new ResponseEntity<>(manufacturer, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/api/manufacturer/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        manufacturerRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
