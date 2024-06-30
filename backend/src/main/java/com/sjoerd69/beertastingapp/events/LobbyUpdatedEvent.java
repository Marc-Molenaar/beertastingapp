package com.sjoerd69.beertastingapp.events;

import com.sjoerd69.beertastingapp.models.Lobby;
import org.springframework.context.ApplicationEvent;

public class LobbyUpdatedEvent extends ApplicationEvent {
    private final Long lobbyId;

    public LobbyUpdatedEvent(Object source, Long lobbyId) {
        super(source);
        this.lobbyId = lobbyId;
    }

    public Long getLobbyId() {
        return lobbyId;
    }
}
