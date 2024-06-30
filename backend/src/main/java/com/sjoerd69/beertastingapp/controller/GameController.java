package com.sjoerd69.beertastingapp.controller;

import com.sjoerd69.beertastingapp.dto.GameDTO;
import com.sjoerd69.beertastingapp.models.Game;
import com.sjoerd69.beertastingapp.models.Lobby;
import com.sjoerd69.beertastingapp.services.GameService;
import com.sjoerd69.beertastingapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    private final UserService userService;

    public GameController(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames(){
        return ResponseEntity.ok(this.gameService.getAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id){
        return ResponseEntity.ok(this.gameService.getGameById(id));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createGame(@RequestBody GameDTO gameDTO,
                                                          @RequestHeader("Authorization") String bearerToken){
        if(!this.userService.authorizeAdmin(bearerToken)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }

        this.gameService.createGame(gameDTO, bearerToken);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Game created");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<Lobby> startGame(@PathVariable Long id,
                                                         @RequestHeader("Authorization") String bearerToken){
        if(!this.userService.authorizeAdmin(bearerToken)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }

        Lobby lobby = this.gameService.startGame(id);

        return ResponseEntity.ok(lobby);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateGameById(@PathVariable Long id, @RequestBody GameDTO gameDTO,
                                                              @RequestHeader("Authorization") String bearerToken){
        if(!this.userService.authorizeAdmin(bearerToken)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }

        this.gameService.updateGame(gameDTO, id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Game updated");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteGameById(@PathVariable Long id,
                                                              @RequestHeader("Authorization") String bearerToken){
        if(!this.userService.authorizeAdmin(bearerToken)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }

        this.gameService.deleteGameById(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Game deleted");
        return ResponseEntity.ok(response);
    }
}
