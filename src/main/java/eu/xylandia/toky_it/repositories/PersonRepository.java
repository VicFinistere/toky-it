package eu.xylandia.toky_it.repositories;

import eu.xylandia.toky_it.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByName(String name);
    Person findById(long id);
}
