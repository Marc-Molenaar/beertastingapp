package com.sjoerd69.beertastingapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import com.sjoerd69.beertastingapp.models.Answer;

import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Lobby lobby;

    @ManyToOne
    private CustomUser user;

    @OneToMany(mappedBy = "player")
    @JsonBackReference
    private List<Answer> answers;

    @OneToMany(mappedBy = "player")
    @JsonBackReference
    private List<Result> results;

    private int points;

    public Player(Lobby lobby, CustomUser user, int points) {
        this.lobby = lobby;
        this.user = user;
        this.points = points;
    }

    public Player() {
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
