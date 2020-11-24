package com.zoe.commerce.controller;

import com.zoe.commerce.model.Client;
import com.zoe.commerce.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @GetMapping(value = "/client/id{id}")
    public Optional<Client> findClientById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @GetMapping(value = "/client/{name}")
    public List<Client> findClientByName(@PathVariable("name") String name) {
        return repository.findByName(name);
    }
}