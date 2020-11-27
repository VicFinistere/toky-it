package eu.xylandia.toky_it.controller;

import eu.xylandia.toky_it.model.Person;
import eu.xylandia.toky_it.model.Question;
import eu.xylandia.toky_it.repositories.PersonRepository;
import eu.xylandia.toky_it.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MainRestController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/getInput")
    public String getInput(@RequestParam Optional<String> user_input) {

        String msg = "User input : ";

        if(user_input. isPresent()) {
            msg += user_input.get();
            questionRepository.save(new Question(new Person("JohnDoe"), user_input.get()));
        }


        return msg;
    }
}
