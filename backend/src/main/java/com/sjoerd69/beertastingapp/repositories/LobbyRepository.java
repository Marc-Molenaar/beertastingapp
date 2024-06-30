package com.sjoerd69.beertastingapp.repositories;

import com.sjoerd69.beertastingapp.models.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LobbyRepository extends JpaRepository<Lobby, Long> {
}
