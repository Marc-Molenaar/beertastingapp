package com.sjoerd69.beertastingapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sjoerd69.beertastingapp.models.enums.ScreenType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "screens")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Game game;

    @OneToMany(mappedBy = "screen")
    @JsonBackReference
    private List<Flow> flows;

    @OneToMany(mappedBy = "screen")
    @JsonBackReference
    private List<Input> inputs;

    @OneToMany(mappedBy = "currentScreen")
    @JsonBackReference
    private List<Lobby> lobbies;

    private Enum<ScreenType> type;

    private String title;

    private String description;

    public Screen(Game game, List<Flow> flows, List<Input> inputs, List<Lobby> lobbies, Enum<ScreenType> type, String title, String description) {
        this.game = game;
        this.flows = flows;
        this.inputs = inputs;
        this.lobbies = lobbies;
        this.type = type;
        this.title = title;
        this.description = description;
    }

    public Screen() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    public List<Lobby> getLobbies() {
        return lobbies;
    }

    public void setLobbies(List<Lobby> lobbies) {
        this.lobbies = lobbies;
    }

    public Enum<ScreenType> getType() {
        return type;
    }

    public void setType(Enum<ScreenType> type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
