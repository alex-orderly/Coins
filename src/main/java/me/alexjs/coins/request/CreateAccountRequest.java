package me.alexjs.coins.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CreateAccountRequest {

    private final String name;

    @JsonCreator
    public CreateAccountRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
