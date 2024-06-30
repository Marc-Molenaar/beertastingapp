package com.sjoerd69.beertastingapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "input_options")
public class InputOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Input input;

    private String value;

    private boolean isCorrect;

    public InputOption(Input input, String value, boolean isCorrect) {
        this.input = input;
        this.value = value;
        this.isCorrect = isCorrect;
    }

    public InputOption() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
