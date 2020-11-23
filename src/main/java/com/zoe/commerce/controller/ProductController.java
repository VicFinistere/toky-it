package com.zoe.commerce.controller;

import com.zoe.commerce.repositories.ProductRepository;
import com.zoe.commerce.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value="/product")
    public String product() {
        return "C'est la page des produits";
    }

    @GetMapping(value="/product/test/{id}")
    public String findProductById(@PathVariable("id") int id) {
        return "product : " + id;
    }

    @GetMapping(value="/product/{id}")
    public Optional<Product> findProductById(@PathVariable("id") Long id) {
        return this.productRepository.findById(id);
    }

    @GetMapping(value="/product/{name}")
    public Optional<Product> findProductByName(@PathVariable("name") String name){
        return this.productRepository.findByName(name);
    }


}