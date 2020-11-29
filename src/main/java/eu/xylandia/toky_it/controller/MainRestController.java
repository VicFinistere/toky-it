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

    public static final String TECHNOLOGIES = "Technologies";
    public static final String CODE = "Code";

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
        question.getAnswers().add(new Answer(new Person("John Doe"), givenAnswer));
        questionRepository.save(question);
    }

    @GetMapping("/getQuestions")
    public Iterable<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/getQuestionsWithFilter")
    public Iterable<Question> getQuestionsWithFilter(@RequestParam String filter) {
        Iterable<Question> result;

        // code
        if (filter.contains("code")) {
            result = questionRepository.findQuestionByQuestionMessageContaining(CODE);
        }

        // info
        else if (filter.contains("info")) {
            result = questionRepository.findQuestionByQuestionMessageContaining(TECHNOLOGIES);
        }

        // answeredIT
        else if (filter.contains("answeredIT")) {
            List<Question> answeredQuestions = new ArrayList<>();

            List<Question> questions = questionRepository.findQuestionByQuestionMessageContaining(CODE);
            questions.addAll(questionRepository.findQuestionByQuestionMessageContaining(TECHNOLOGIES));

            for (Question question : questions) {
                if (question.isAnsweredQuestion()) {
                    answeredQuestions.add(question);
                }

            }

            result = answeredQuestions;
        }

        // answered
        else if (filter.contains("answered")) {
            List<Question> answeredQuestions = new ArrayList<>();
            List<Question> questions = questionRepository.findQuestionByQuestionMessageNotContaining(CODE);
            questions.addAll(questionRepository.findQuestionByQuestionMessageNotContaining(TECHNOLOGIES));
            for (Question question : questions) {
                if (question.isAnsweredQuestion()) {
                    answeredQuestions.add(question);
                }

            }
            result = answeredQuestions;
        }

        // spam
        else if (filter.contains("spam")) {
            List<Question> spamQuestions = new ArrayList<>();
            Iterable<Answer> answersList = answerRepository.findByAnswerContaining("not an IT question");
            for (Answer answers : answersList) {
                spamQuestions.addAll(questionRepository.findQuestionByAnswers(answers));
            }
            result = spamQuestions;

        } else {
            result = questionRepository.findAll();
        }

        return result;
    }


    @PostMapping("/getQuestion")
    public Question getQuestion(@RequestParam long idQuestion) {
        return questionRepository.findById(idQuestion);
    }

    @PostMapping("/removeQuestion")
    public void removeQuestion(@RequestParam String question) {
        Question questionToDelete = questionRepository.findByQuestionMessage(question).get(0);
        questionRepository.delete(questionToDelete);
    }
}
