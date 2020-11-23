package com.zoe.commerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String provider;

    protected Product() {
    }

    public Product(String name) {
        this.name = name;
        this.provider = "Undefined";
    }

    public Product(String name, String provider) {
        this.name = name;
        this.provider = provider;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, name='%s', provider='%s']",
                id, name, provider);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

}
