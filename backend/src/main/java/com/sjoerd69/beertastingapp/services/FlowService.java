package com.sjoerd69.beertastingapp.services;

import com.sjoerd69.beertastingapp.models.Flow;
import com.sjoerd69.beertastingapp.models.Game;
import com.sjoerd69.beertastingapp.repositories.FlowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowService {

    private final FlowRepository flowRepository;

    public FlowService(FlowRepository flowRepository) {
        this.flowRepository = flowRepository;
    }

    public List<Flow> getFlowByGame(Game game) {
        return this.flowRepository.findByGameOrderByPosition(game);
    }
}
