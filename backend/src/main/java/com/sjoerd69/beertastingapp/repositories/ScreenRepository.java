package com.sjoerd69.beertastingapp.repositories;

import com.sjoerd69.beertastingapp.models.Game;
import com.sjoerd69.beertastingapp.models.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
}
