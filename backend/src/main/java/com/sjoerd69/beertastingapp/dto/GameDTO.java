package com.sjoerd69.beertastingapp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.sjoerd69.beertastingapp.models.enums.Status;

public class GameDTO {
    public String name;
    public String description;

    @JsonAlias("owner_id")
    public long ownerId;

    public Enum<Status> status;

    public GameDTO(String name, String description, long ownerId, Enum<Status> status) {
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

    public Enum<Status> getStatus() {
        return status;
    }

    public void setStatus(Enum<Status> status) {
        this.status = status;
    }
}
