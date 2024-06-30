package com.sjoerd69.beertastingapp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.sjoerd69.beertastingapp.models.enums.GameStatus;

public class GameDTO {
    public String name;
    public String description;

    @JsonAlias("owner_id")
    public long ownerId;

    public String status;

    public GameDTO(String name, String description, long ownerId, String status) {
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.status = status;
    }

    public GameDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public Enum<GameStatus> getStatus() {
        return GameStatus.valueOf(this.status);
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
