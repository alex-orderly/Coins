package me.alexjs.coins.request;

public class CreateAccountRequest {

    private String name;

    public CreateAccountRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
