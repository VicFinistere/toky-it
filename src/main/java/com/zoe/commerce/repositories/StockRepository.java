package com.zoe.commerce.repositories;

import com.zoe.commerce.model.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findByProduct_Name(String name);
    Stock findById(long id);
}
