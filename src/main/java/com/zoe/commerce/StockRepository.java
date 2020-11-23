package com.zoe.commerce;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findByProduct_Name(String name);
    Stock findById(long id);
}
