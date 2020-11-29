package eu.xylandia.toky_it.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity(name="question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = {CascadeType.ALL})
    private Person person;
    private String question;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Answer> answers;

    protected Question() {}

    public Question(Person person, String question) {
        this.person = person;
        this.question = question;
        this.answers = new ArrayList<>();
    }

    public Question(Person person, String question, List<Answer> answer) {
        this.person = person;
        this.question = question;
        this.answers = answer;
    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswer(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean isAnsweredQuestion() {
        boolean result = false;
        for (Answer answer : answers) {
            if (answer.getAnswer() != null) {
                result = true;
                break;
            }
        }
        return result;
    }
}
