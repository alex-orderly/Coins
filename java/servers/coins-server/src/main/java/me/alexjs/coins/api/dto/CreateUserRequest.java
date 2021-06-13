package me.alexjs.coins.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CreateUserRequest {

    private String firstName;
    private String lastName;
    private String password;

    public CreateUserRequest() {
    }

    @JsonCreator
    public CreateUserRequest(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
