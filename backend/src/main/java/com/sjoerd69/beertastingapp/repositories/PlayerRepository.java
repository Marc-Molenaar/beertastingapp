package com.sjoerd69.beertastingapp.repositories;

import com.sjoerd69.beertastingapp.models.CustomUser;
import com.sjoerd69.beertastingapp.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByUserAndLobbyId(CustomUser user, Long id);
}
