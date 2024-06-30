package com.sjoerd69.beertastingapp.services;

import com.sjoerd69.beertastingapp.models.CustomUser;
import com.sjoerd69.beertastingapp.models.Lobby;
import com.sjoerd69.beertastingapp.models.Player;
import com.sjoerd69.beertastingapp.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerDAO;

    public PlayerService(PlayerRepository playerDAO) {
        this.playerDAO = playerDAO;
    }

    public Player createPlayer(CustomUser user, Lobby lobby) {
        Player player = new Player(lobby, user, 0);
        return this.playerDAO.save(player);
    }

    public Player getPlayerByUserAndLobby(CustomUser user, Long id) {
        return this.playerDAO.findByUserAndLobbyId(user, id);
    }

    public void removePlayer(Player player) {
        this.playerDAO.delete(player);
    }
}
