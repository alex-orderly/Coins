package me.alexjs.coins.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

public class CreateAccountResponse {

    private UUID id;
    private String name;

    public CreateAccountResponse() {
    }

    @JsonCreator
    public CreateAccountResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
