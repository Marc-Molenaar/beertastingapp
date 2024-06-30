package com.sjoerd69.beertastingapp.repositories;

import com.sjoerd69.beertastingapp.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//maps the product class to the database using the Long type as default of ID's
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
