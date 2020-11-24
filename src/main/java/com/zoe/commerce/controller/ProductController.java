package com.zoe.commerce.controller;

import com.zoe.commerce.model.Product;
import com.zoe.commerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/produit")
public class ProductController {

    @Autowired
    private ProductRepository repository;


    @GetMapping(value = "/{id}")
    public Optional<Product> findProductById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @GetMapping(value = "/{name}")
    public Optional<Product> findProductByName(@PathVariable("name") String name) {
        return repository.findByName(name);
    }
}