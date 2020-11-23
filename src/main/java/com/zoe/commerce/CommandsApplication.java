package com.zoe.commerce;

import com.zoe.commerce.model.Product;
import com.zoe.commerce.model.Stock;
import com.zoe.commerce.repositories.ProductRepository;
import com.zoe.commerce.repositories.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CommandsApplication {

    private static final Logger log = LoggerFactory.getLogger(CommandsApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(CommandsApplication.class, args);
    }

    @Bean
    public CommandLineRunner productBean(ProductRepository repository) {
        return (args) -> {

            // save a few Products
            repository.save(new Product("orange"));
            repository.save(new Product("rouge"));
            repository.save(new Product("bleu"));
            repository.save(new Product("vert"));
            repository.save(new Product("rose"));

            // fetch all Commands
            log.info("Product found with findAll():");
            log.info("-------------------------------");
            for (Product Product : repository.findAll()) {
                log.info(Product.toString());
            }
            log.info("");
        };
    }

    @Bean
    public CommandLineRunner stock(StockRepository repository) {
        return (args) -> {

            repository.save(new Stock(new Product("orange"), 14));
            repository.save(new Stock(new Product("orange")));


            // fetch all Stock
            log.info("Stock found with findAll():");
            log.info("-------------------------------");
            for (Stock Stock : repository.findAll()) {
                log.info(Stock.toString());
            }
            log.info("");
        };
    }

}