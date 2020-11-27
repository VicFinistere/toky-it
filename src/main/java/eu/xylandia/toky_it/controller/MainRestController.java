package eu.xylandia.toky_it.controller;

import eu.xylandia.toky_it.model.Person;
import eu.xylandia.toky_it.model.Question;
import eu.xylandia.toky_it.repositories.PersonRepository;
import eu.xylandia.toky_it.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MainRestController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/getInput")
    public String getInput(@RequestParam Optional<String> userInput) {

        String msg = "User input : ";

        if(userInput. isPresent()) {
            msg += userInput.get();
            questionRepository.save(new Question(new Person("JohnDoe"), userInput.get()));
        }


        return msg;
    }

    @GetMapping("/getQuestions")
    public List<String> getQuestions() {
        Iterable<Question> q = questionRepository.findAll();
        List<String> questions = new ArrayList<>();
        for (Question question : q) {
            questions.add(question.getQuestion());
        }
        return questions;
    }
}
