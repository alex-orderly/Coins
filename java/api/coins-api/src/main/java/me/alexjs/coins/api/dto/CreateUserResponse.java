package me.alexjs.coins.api.dto;

public class CreateUserResponse {

    private String username;
    private String firstName;
    private String lastName;

    public CreateUserResponse() {
    }

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
