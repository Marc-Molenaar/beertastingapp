package com.sjoerd69.beertastingapp.repositories;

import com.sjoerd69.beertastingapp.models.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
    CustomUser findByEmail(String email);
}
