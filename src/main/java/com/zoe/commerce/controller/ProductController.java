package com.zoe.commerce.controller;

import com.zoe.commerce.model.Product;
import com.zoe.commerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product")
    public String product() {
        return "C'est la page des produits";
    }

    @GetMapping(value = "/product/test/{id}")
    public String findProductById(@PathVariable("id") int id) {
        return "product : " + id;
    }

    @GetMapping(value="/produit/{id}")
    public Optional<Product> findProductByName(@PathVariable("id") Long id){
        return productService.findById(id);
    }


}