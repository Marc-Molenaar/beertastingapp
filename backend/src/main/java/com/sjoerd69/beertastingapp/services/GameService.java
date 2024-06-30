package com.sjoerd69.beertastingapp.services;

import com.sjoerd69.beertastingapp.dao.GameDAO;
import com.sjoerd69.beertastingapp.models.Lobby;
import com.sjoerd69.beertastingapp.models.enums.GameStatus;
import com.sjoerd69.beertastingapp.repositories.UserRepository;
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
    private final UserService userService;
    private final LobbyService lobbyService;

    public GameService(GameDAO gameDAO, UserService userService, LobbyService lobbyService) {
        this.gameDAO = gameDAO;
        this.userService = userService;
        this.lobbyService = lobbyService;
    }

    public List<Game> getAllGames() {
        return this.gameDAO.findAllGames();
    }

    @Transactional
    public void createGame(GameDTO gameDTO, String bearerToken) {
        CustomUser user = this.userService.getUserByBearerToken(bearerToken);

        Game game = new Game(gameDTO.getName(), gameDTO.getDescription(), user, gameDTO.getStatus());
        this.gameDAO.saveGame(game);
    }

    public void updateGame(GameDTO gameDTO, Long id) {
        Optional<Game> gameOptional = this.gameDAO.findGameById(id);

        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            game.setName(gameDTO.getName());
            game.setDescription(gameDTO.getDescription());
            game.setStatus(gameDTO.getStatus());

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

    public Lobby startGame(Long id) {
        Optional<Game> game = this.gameDAO.findGameById(id);

        if (game.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No game found with that id"
            );
        }

        return this.lobbyService.createLobby(game.get());
    }
}
