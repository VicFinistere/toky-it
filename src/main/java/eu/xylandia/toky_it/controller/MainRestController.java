package eu.xylandia.toky_it.controller;

import eu.xylandia.toky_it.model.Answer;
import eu.xylandia.toky_it.model.Person;
import eu.xylandia.toky_it.model.Question;
import eu.xylandia.toky_it.repositories.AnswerRepository;
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

    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping("/getInput")
    public String getInput(@RequestParam Optional<String> userInput) {

        String msg = "User input : ";

        if (userInput.isPresent()) {
            msg += userInput.get();

            // TODO : Handle question's author
            questionRepository.save(new Question(new Person("JohnDoe"), userInput.get()));
        }


        return msg;
    }

    @PostMapping("/setAnswer")
    public void setAnswer(@RequestParam long selectedQuestionId, @RequestParam String givenAnswer) {

        Question question = questionRepository.findById(selectedQuestionId);
        question.getAnswer().add(new Answer(new Person("John Doe"), givenAnswer));
        questionRepository.save(question);
    }

    @GetMapping("/getQuestions")
    public Iterable<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/getQuestionsWithFilter")
    public Iterable<Question> getQuestionsWithFilter(@RequestParam String filter) {

        // code
        if(filter.contains("code")){
           return questionRepository.findQuestionByQuestionContaining("Code");
        }

        // info
        else if(filter.contains("info")){
            return questionRepository.findQuestionByQuestionContaining("Technologies");
        }

        // answeredIT
        else if(filter.contains("answeredIT")){
            List<Question> answeredQuestions = new ArrayList<>();

            List<Question> questions = questionRepository.findQuestionByQuestionContaining("Code");
            questions.addAll(questionRepository.findQuestionByQuestionContaining("Technologies"));

            for(Question question: questions){
                if(isAnsweredQuestion(question)){
                    answeredQuestions.add(question);
                }

            }

            return answeredQuestions;
        }

        // answered
        else if(filter.contains("answered")){
            List<Question> answeredQuestions = new ArrayList<>();
            List<Question> questions = questionRepository.findQuestionByQuestionNotContainingAndQuestionNotContaining("Code", "Technologies");
            for(Question question: questions){
                if(isAnsweredQuestion(question)){
                    answeredQuestions.add(question);
                }

            }
            return answeredQuestions;
        }

        // spam
        else if(filter.contains("spam")){

            Iterable<Answer> answers = answerRepository.findByAnswerIsNotContaining("not an IT question");
            return questionRepository.findQuestionByAnswer(answers);

        }

        return questionRepository.findAll();
    }

    private boolean isAnsweredQuestion(Question question) {
        boolean result = false;
        List<Answer> answers = question.getAnswer();
        for (Answer answer : answers) {
            if (answer.getAnswer() != null) {
                result = true;
                break;
            }
        }
        return result;
    }


    @PostMapping("/getQuestion")
    public Question getQuestion(@RequestParam long idQuestion) {
        return questionRepository.findById(idQuestion);
    }

    @PostMapping("/removeQuestion")
    public void removeQuestion(@RequestParam String question) {
        Question questionToDelete = questionRepository.findByQuestion(question).get(0);
        questionRepository.delete(questionToDelete);
    }
}
