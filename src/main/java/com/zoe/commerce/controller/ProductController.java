package com.zoe.commerce.controller;

import com.zoe.commerce.model.Product;
import com.zoe.commerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping(value = "/product")
    public String product() {
        return "C'est la page des produits";
    }

    @GetMapping(value = "/product/test/{id}")
    public String findProductById(@PathVariable("id") int id) {
        return "product : " + id;
    }

    @GetMapping(value = "/produit/{id}")
    public Optional<Product> findProductById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

}