package com.sjoerd69.beertastingapp.services;

import com.sjoerd69.beertastingapp.events.LobbyUpdatedEvent;
import com.sjoerd69.beertastingapp.models.*;
import com.sjoerd69.beertastingapp.models.enums.LobbyStatus;
import com.sjoerd69.beertastingapp.repositories.LobbyRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LobbyService {
    private final LobbyRepository lobbyDAO;
    private final PlayerService playerService;
    private final UserService userService;
    private final ScreenService screenService;
    private final ApplicationEventPublisher eventPublisher;

    public LobbyService(LobbyRepository lobbyDAO, PlayerService playerService, UserService userService,
                        ScreenService screenService, ApplicationEventPublisher eventPublisher) {
        this.lobbyDAO = lobbyDAO;
        this.playerService = playerService;
        this.userService = userService;
        this.screenService = screenService;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Lobby getLobbyById(Long id) {
        Lobby lobby = this.lobbyDAO.findById(id).orElse(null);
        if (lobby != null) {
            // Initialize the players collection
            lobby.getPlayers().size();
        }
        return lobby;
    }

    @Transactional
    public Lobby joinLobby(Long id, String bearerToken) {
        CustomUser user = this.userService.getUserByBearerToken(bearerToken);
        Lobby lobby = this.lobbyDAO.findById(id).orElse(null);

        if (lobby != null && this.playerService.getPlayerByUserAndLobby(user, id) != null) {
            this.eventPublisher.publishEvent(new LobbyUpdatedEvent(this, id));
            return lobby;
        }

        if (lobby != null && lobby.getPlayers().size() >= lobby.getMaxPlayers()) {
            this.eventPublisher.publishEvent(new LobbyUpdatedEvent(this, id));
            return lobby;
        }

        Player player = this.playerService.createPlayer(user, lobby);
        lobby.getPlayers().add(player);

        this.eventPublisher.publishEvent(new LobbyUpdatedEvent(this, id));

        return lobby;
    }

    @Transactional
    public Lobby nextScreen(Long id) {
        Lobby lobby = this.lobbyDAO.findById(id).orElse(null);
        Screen screen = this.screenService.getNextScreenByGameAndFlow(lobby.getGame(), lobby.getCurrentScreen());

        lobby.setCurrentScreen(screen);
        this.lobbyDAO.save(lobby);

        // Refresh the lobby object
        lobby = this.lobbyDAO.findById(id).orElse(null);
        if (lobby != null) {
            lobby.getPlayers().size(); // Initialize the players collection
        }

        this.eventPublisher.publishEvent(new LobbyUpdatedEvent(this, id));

        return lobby;
    }

    @Transactional
    public Boolean leaveLobby(Long id, String bearerToken) {
        CustomUser user = this.userService.getUserByBearerToken(bearerToken);
        Player player = this.playerService.getPlayerByUserAndLobby(user, id);

        this.playerService.removePlayer(player);

        // Refresh the lobby object
        Lobby lobby = this.lobbyDAO.findById(id).orElse(null);
        if (lobby != null) {
            lobby.getPlayers().size(); // Initialize the players collection
        }

        this.eventPublisher.publishEvent(new LobbyUpdatedEvent(this, id));

        return true;
    }

    @Transactional
    public Lobby createLobby(Game game) {
        int code = (int) (Math.random() * 900000) + 100000;
        Date expirationDate = new Date(System.currentTimeMillis() + 3600000);
        Screen screen = this.screenService.getFirstScreenByGameAndFlow(game);

        int maxPlayers = 10;
        Enum<LobbyStatus> status = LobbyStatus.WAITING;

        Lobby lobby = new Lobby(code, expirationDate, screen, maxPlayers, status, game);
        return this.lobbyDAO.save(lobby);
    }
}
