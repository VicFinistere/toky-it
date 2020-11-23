package com.zoe.commerce.controller;

import com.zoe.commerce.repositories.ClientRepository;
import com.zoe.commerce.repositories.ProductRepository;
import com.zoe.commerce.repositories.StockRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final ClientRepository clientRepository;

    public MainController(ProductRepository productRepository,
                          StockRepository stockRepository,
                          ClientRepository clientRepository) {

        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.clientRepository = clientRepository;
    }

    @RequestMapping(value = "/")
    public String index() {
        return "Hello World";
    }

}