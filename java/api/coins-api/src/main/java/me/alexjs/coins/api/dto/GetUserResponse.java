package me.alexjs.coins.api.dto;

public class GetUserResponse {

    private String username;
    private String firstName;
    private String lastName;
    private String passwordHash;

    public GetUserResponse() {
    }

    public GetUserResponse(String username, String firstName, String lastName, String passwordHash) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
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

    public String getPasswordHash() {
        return passwordHash;
    }

}
