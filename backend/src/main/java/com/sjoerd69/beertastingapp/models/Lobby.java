package com.sjoerd69.beertastingapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sjoerd69.beertastingapp.models.enums.LobbyStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lobbies")
public class Lobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int code;

    private Date expiration;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen currentScreen;

    private int maxPlayers;

    private Enum<LobbyStatus> status;

    @ManyToOne
    private Game game;

    @OneToMany(mappedBy = "lobby")
    @JsonBackReference
    private List<Player> players;

    @OneToMany(mappedBy = "lobby")
    @JsonBackReference
    private List<Result> results;

    @OneToMany(mappedBy = "lobby")
    @JsonBackReference
    private List<Answer> answers;

    public Lobby(int code, Date expiration, Screen currentScreen, int maxPlayers, Enum<LobbyStatus> status, Game game) {
        this.code = code;
        this.expiration = expiration;
        this.currentScreen = currentScreen;
        this.maxPlayers = maxPlayers;
        this.status = status;
        this.game = game;
    }

    public Lobby() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(Screen currentScreen) {
        this.currentScreen = currentScreen;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Enum<LobbyStatus> getStatus() {
        return status;
    }

    public void setStatus(Enum<LobbyStatus> status) {
        this.status = status;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
