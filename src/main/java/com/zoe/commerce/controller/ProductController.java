package com.zoe.commerce.controller;

import com.zoe.commerce.model.Product;
import com.zoe.commerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping(value = "/produit/{id}")
    public Optional<Product> getProductById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @GetMapping(value = "/produit/{name}")
    public List<Product> getProductByName(@PathVariable("name") String name) {
        return repository.findByName(name);
    }

    @PostMapping(value = "/produit")
    public List<Product> postProduct(@RequestParam("name") String name) {
        repository.save(new Product(name));
        return repository.findByName(name);
    }
}