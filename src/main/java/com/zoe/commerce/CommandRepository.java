package com.zoe.commerce;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface CommandRepository extends CrudRepository<Command, Long> {
    List<Command> findByDate(Date date);
    Command findById(long id);
}
