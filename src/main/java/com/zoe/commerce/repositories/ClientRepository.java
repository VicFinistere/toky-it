package com.zoe.commerce.repositories;

import com.zoe.commerce.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findByName(String name);
    Client findById(long id);
}
