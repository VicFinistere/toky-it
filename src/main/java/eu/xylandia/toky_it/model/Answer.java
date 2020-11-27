package eu.xylandia.toky_it.model;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = {CascadeType.ALL})
    private Person coach;
    private String answer;
    protected Answer(){};

    public Answer(Person coach, String answer) {
        this.coach = coach;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public Person getCoach() {
        return coach;
    }

    public void setCoach(Person coach) {
        this.coach = coach;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
