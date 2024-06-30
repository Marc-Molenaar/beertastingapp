package com.sjoerd69.beertastingapp.controller;

import com.sjoerd69.beertastingapp.models.Lobby;
import com.sjoerd69.beertastingapp.services.LobbyService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/lobbies")
public class LobbyController {
    private final LobbyService lobbyService;

    public LobbyController(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lobby> getLobbyById(@PathVariable Long id) {
        return ResponseEntity.ok(this.lobbyService.getLobbyById(id));
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<Lobby> joinLobby(@PathVariable Long id, @RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(this.lobbyService.joinLobby(id, bearerToken));
    }

    @PostMapping("/{id}/leave")
    public ResponseEntity<Boolean> leaveLobby(@PathVariable Long id, @RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(this.lobbyService.leaveLobby(id, bearerToken));
    }
}
