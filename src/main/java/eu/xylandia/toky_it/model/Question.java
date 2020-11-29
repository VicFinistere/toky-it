package eu.xylandia.toky_it.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = {CascadeType.ALL})
    private Person person;
    private String questionMessage;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Answer> answers;

    protected Question() {}

    public Question(Person person, String questionMessage) {
        this.person = person;
        this.questionMessage = questionMessage;
        this.answers = new ArrayList<>();
    }

    public Question(Person person, String questionMessage, List<Answer> answers) {
        this.person = person;
        this.questionMessage = questionMessage;
        this.answers = answers;
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

    public String getQuestionMessage() {
        return questionMessage;
    }

    public void setQuestionMessage(String question) {
        this.questionMessage = question;
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
