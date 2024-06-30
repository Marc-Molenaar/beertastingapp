package com.sjoerd69.beertastingapp.dao;

import com.sjoerd69.beertastingapp.models.Game;
import com.sjoerd69.beertastingapp.repositories.GameRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class GameDAO {

    private final GameRepository gameRepository;

    public GameDAO(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> findAllGames() {
        return this.gameRepository.findAll();
    }

    public Optional<Game> findGameById(Long id) {
        return this.gameRepository.findById(id);
    }

    public void saveGame(Game game) {
        this.gameRepository.save(game);
    }

    public void deleteGame(Game game) {
        this.gameRepository.delete(game);
    }
}
