package me.alexjs.coins.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CreateUserResponse {

    private String username;
    private String firstName;
    private String lastName;

    public CreateUserResponse() {
    }

    @JsonCreator
    public CreateUserResponse(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
