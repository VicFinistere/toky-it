package com.zoe.commerce;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByName(String name);
    Product findById(long id);
}
