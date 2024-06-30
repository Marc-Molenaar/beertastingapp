package com.sjoerd69.beertastingapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sjoerd69.beertastingapp.models.enums.GameStatus;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JsonIgnore
    private CustomUser owner;

    @OneToMany
    @JsonIgnore
    private Set<Lobby> lobbies;

    @OneToMany
    @JsonIgnore
    private Set<Screen> screens;

    @OneToMany
    @JsonIgnore
    private Set<Flow> flows;

    private Enum<GameStatus> status;


    public Game() {
    }

    public Game(String name, String description, CustomUser owner, Enum<GameStatus> status) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CustomUser getOwner() {
        return owner;
    }

    public void setOwner(CustomUser owner) {
        this.owner = owner;
    }

    public Enum<GameStatus> getStatus() {
        return status;
    }

    public void setStatus(Enum<GameStatus> status) {
        this.status = status;
    }

    public Set<Lobby> getLobbies() {
        return lobbies;
    }

    public void setLobbies(Set<Lobby> lobbies) {
        this.lobbies = lobbies;
    }

    public Set<Screen> getScreens() {
        return screens;
    }

    public void setScreens(Set<Screen> screens) {
        this.screens = screens;
    }

    public Set<Flow> getFlows() {
        return flows;
    }

    public void setFlows(Set<Flow> flows) {
        this.flows = flows;
    }
}

