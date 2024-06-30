package com.sjoerd69.beertastingapp.controller;

import com.sjoerd69.beertastingapp.events.LobbyUpdatedEvent;
import com.sjoerd69.beertastingapp.models.Lobby;
import com.sjoerd69.beertastingapp.services.LobbyService;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SubscribeMapping;

import java.util.Map;

@Controller
public class LobbyWSController {
    private final LobbyService lobbyService;
    private final SimpMessagingTemplate messagingTemplate;

    public LobbyWSController(LobbyService lobbyService, SimpMessagingTemplate messagingTemplate) {
        this.lobbyService = lobbyService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/lobby/{id}")
    @SendTo("/topic/lobby/{id}")
    public Lobby lobby(@DestinationVariable Long id) {
        return this.lobbyService.getLobbyById(id);
    }

    @MessageMapping("/lobby/{id}/join")
    public ResponseEntity<Lobby> joinLobby(@DestinationVariable Long id, @Payload Map<String, String> payload) {
        String bearerToken = payload.get("Authorization");
        return ResponseEntity.ok(this.lobbyService.joinLobby(id, bearerToken));
    }

    @EventListener
    public void handleLobbyUpdatedEvent(LobbyUpdatedEvent event) {
        Lobby updatedLobby = this.lobbyService.getLobbyById(event.getLobbyId());
        this.messagingTemplate.convertAndSend("/topic/lobby/" + event.getLobbyId(), updatedLobby);
    }
}
