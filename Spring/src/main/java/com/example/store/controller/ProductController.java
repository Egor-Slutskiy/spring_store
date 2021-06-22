package com.example.store.controller;

import com.example.store.entity.Product;
import com.example.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping(value = "/api/product")
    public ResponseEntity<?> create(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/product")
    public ResponseEntity<?> findAll() {
        final List<Product> productList = productRepository.findAll();

        if(productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/api/product/{id}")
    public ResponseEntity<?> find(@PathVariable(name = "id") Long id) {
        final Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/api/product/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        productRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
