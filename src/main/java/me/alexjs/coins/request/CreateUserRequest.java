package me.alexjs.coins.request;

public class CreateUserRequest {

    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;

    public CreateUserRequest(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
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
