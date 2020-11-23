package com.zoe.commerce.repositories;

import com.zoe.commerce.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Product findById(long id);
}
