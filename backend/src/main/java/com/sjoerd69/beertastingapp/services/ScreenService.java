package com.sjoerd69.beertastingapp.services;

import com.sjoerd69.beertastingapp.models.Flow;
import com.sjoerd69.beertastingapp.models.Game;
import com.sjoerd69.beertastingapp.models.Screen;
import com.sjoerd69.beertastingapp.repositories.ScreenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {

    private final ScreenRepository screenRepository;
    private final FlowService flowService;

    public ScreenService(ScreenRepository screenRepository,
                         FlowService flowService) {
        this.screenRepository = screenRepository;
        this.flowService = flowService;
    }

    public Screen getNextScreenByGameAndFlow(Game game, Screen screen) {
        List<Flow> flowOptional = this.flowService.getFlowByGame(game);

        if (flowOptional.isEmpty()) {
            return null;
        }

        for (int i = 0; i < flowOptional.size(); i++) {
            if (flowOptional.get(i).getScreen().equals(screen)) {
                if (i + 1 < flowOptional.size()) {
                    return flowOptional.get(i + 1).getScreen();
                }
            }
        }

        return null;
    }

    public Screen getFirstScreenByGameAndFlow(Game game) {
        List<Flow> flowOptional = this.flowService.getFlowByGame(game);

        if (flowOptional.isEmpty()) {
            return null;
        }

        return flowOptional.getFirst().getScreen();
    }
}
