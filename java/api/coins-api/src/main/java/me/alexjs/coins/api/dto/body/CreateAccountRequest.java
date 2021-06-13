package me.alexjs.coins.api.dto.body;

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
