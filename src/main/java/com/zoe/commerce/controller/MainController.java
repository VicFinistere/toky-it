package com.zoe.commerce.controller;

import com.zoe.commerce.repositories.ClientRepository;
import com.zoe.commerce.repositories.ProductRepository;
import com.zoe.commerce.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value = "/")
    public String index() {
        return "Hello World";
    }

}