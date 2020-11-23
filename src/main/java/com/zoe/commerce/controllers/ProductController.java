package com.zoe.commerce.controllers;

import com.zoe.commerce.repositories.ProductRepository;
import com.zoe.commerce.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/produit")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public Optional<Product> findProductById(@PathVariable("id") Long id) {
        return productRepository.findById(id);
    }

    @RequestMapping(value="/{name}", method= RequestMethod.GET)
    public Optional<Product> findProductByName(@PathVariable("name") String name){
        return productRepository.findByName(name);
    }


}