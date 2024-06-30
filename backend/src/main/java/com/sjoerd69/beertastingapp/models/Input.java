package com.sjoerd69.beertastingapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sjoerd69.beertastingapp.models.enums.InputType;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "inputs")
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Screen screen;

    @OneToMany(mappedBy = "input")
    @JsonBackReference
    private List<Answer> answers;

    @OneToMany(mappedBy = "input")
    @JsonBackReference
    private List<InputOption> options;

    private int position;

    private Enum<InputType> type;

    private String label;

    private String placeholder;

    private String correctAnswer;

    public Input(Screen screen, int position, Enum<InputType> type, String label, String placeholder, String correctAnswer) {
        this.screen = screen;
        this.position = position;
        this.type = type;
        this.label = label;
        this.placeholder = placeholder;
        this.correctAnswer = correctAnswer;
    }

    public Input() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Enum<InputType> getType() {
        return type;
    }

    public void setType(Enum<InputType> type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<InputOption> getOptions() {
        return options;
    }

    public void setOptions(List<InputOption> options) {
        this.options = options;
    }
}
