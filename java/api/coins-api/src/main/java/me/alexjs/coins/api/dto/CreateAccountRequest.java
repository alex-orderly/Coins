package me.alexjs.coins.api.dto;

public class CreateAccountRequest {

    private String name;

    public CreateAccountRequest() {
    }

    public CreateAccountRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
