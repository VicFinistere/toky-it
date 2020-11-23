package com.zoe.commandes;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface CommandeRepository extends CrudRepository<Commande, Long> {
    List<Commande> findByDate(Date date);
    Commande findById(long id);
}
