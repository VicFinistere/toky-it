package eu.xylandia.toky_it.repositories;

import eu.xylandia.toky_it.model.Answer;
import eu.xylandia.toky_it.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

    Iterable<Answer> findByAnswerIsNotContaining(String message);
}
