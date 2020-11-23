package com.zoe.commerce.controllers;

import com.zoe.commerce.repositories.ProductRepository;
import com.zoe.commerce.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value="/{id}")
    public Optional<Product> findProductById(@PathVariable("id") Long id) {
        return productRepository.findById(id);
    }

    @GetMapping(value="/{name}")
    public Optional<Product> findProductByName(@PathVariable("name") String name){
        return productRepository.findByName(name);
    }


}