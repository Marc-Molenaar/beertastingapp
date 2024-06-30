package com.sjoerd69.beertastingapp.repositories;

import com.sjoerd69.beertastingapp.models.Flow;
import com.sjoerd69.beertastingapp.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowRepository extends JpaRepository<Flow, Long> {
    List<Flow> findByGame(Game game);

    List<Flow> findByGameOrderByPosition(Game game);

}
