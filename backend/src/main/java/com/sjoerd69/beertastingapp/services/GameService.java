package com.sjoerd69.beertastingapp.services;

import com.sjoerd69.beertastingapp.dao.GameDAO;
import com.sjoerd69.beertastingapp.dao.UserRepository;
import com.sjoerd69.beertastingapp.dto.GameDTO;
import com.sjoerd69.beertastingapp.models.CustomUser;
import com.sjoerd69.beertastingapp.models.Game;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameDAO gameDAO;
    private final UserRepository userRepository;

    public GameService(GameDAO gameDAO, UserRepository userRepository) {
        this.gameDAO = gameDAO;
        this.userRepository = userRepository;
    }

    public List<Game> getAllGames() {
        return this.gameDAO.findAllGames();
    }

    @Transactional
    public void createGame(GameDTO gameDTO) {
        Optional<CustomUser> user = this.userRepository.findById(gameDTO.ownerId);

        if(user.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found"
            );
        }

        Game game = new Game(gameDTO.name, gameDTO.description, user.get(), gameDTO.status);
        this.gameDAO.saveGame(game);
    }

    public void updateGame(GameDTO gameDTO, Long id) {
        Optional<Game> gameOptional = this.gameDAO.findGameById(id);

        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            game.setName(gameDTO.name);
            game.setDescription(gameDTO.description);
            game.setStatus(gameDTO.status);

            this.gameDAO.saveGame(game);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No game found with that id"
            );
        }
    }

    public Game getGameById(Long id) {
        Optional<Game> game = this.gameDAO.findGameById(id);

        if (game.isPresent()) {
            return game.get();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No game found with that id"
            );
        }
    }

    public void deleteGameById(Long id) {
        Optional<Game> game = this.gameDAO.findGameById(id);

        if (game.isPresent()) {
            this.gameDAO.deleteGame(game.get());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No game found with that id"
            );
        }
    }
}
