package me.alexjs.coins.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CreateAccountRequest {

    private String name;

    public CreateAccountRequest() {
    }

    @JsonCreator
    public CreateAccountRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
