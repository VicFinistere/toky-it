package com.zoe.commerce.controller;

import com.zoe.commerce.model.Stock;
import com.zoe.commerce.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StockController {

    @Autowired
    private StockRepository repository;

    @GetMapping(value = "/stock/{id}")
    public Optional<Stock> findStockById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @GetMapping(value = "/stock/{name}")
    public List<Stock> findStockByProduct(@PathVariable("name") String name) {
        return repository.findByProduct_Name(name);
    }
}