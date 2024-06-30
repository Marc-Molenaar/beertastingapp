package com.sjoerd69.beertastingapp.utils;

import com.sjoerd69.beertastingapp.dao.*;
import com.sjoerd69.beertastingapp.models.*;
import com.sjoerd69.beertastingapp.repositories.UserRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


@Component
public class Seeder {
    private final GameDAO gameDAO;
    private final UserRepository userRepository;


    public Seeder(GameDAO gameDAO, UserRepository userRepository) {
        this.gameDAO = gameDAO;
        this.userRepository = userRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event){
        List<CustomUser> users = this.userRepository.findAll();
        if (!users.isEmpty()) { return; }

        this.seedUsers();
    }

    private void seedUsers() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("Test123!");

        CustomUser admin = new CustomUser("admin", "admin@mail.com", password, "ROLE_ADMIN");
        this.userRepository.save(admin);
    }
}
