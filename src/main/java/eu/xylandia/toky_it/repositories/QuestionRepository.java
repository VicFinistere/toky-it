package eu.xylandia.toky_it.repositories;

import eu.xylandia.toky_it.model.Answer;
import eu.xylandia.toky_it.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findByPerson(String name);
    List<Question> findByQuestion(String question);
    Question findById(long id);
    List<Question> findQuestionByQuestionContaining(String string);
    List<Question> findQuestionByQuestionNotContainingAndQuestionNotContaining(String string1, String string2);
    List<Question> findQuestionByAnswer(Answer answer);
}
