package com.sjoerd69.beertastingapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Lobby lobby;

    @ManyToOne
    private Player player;

    private int position;

    private int points;
}
