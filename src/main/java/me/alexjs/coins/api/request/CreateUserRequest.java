package me.alexjs.coins.api.request;

public class CreateUserRequest {

    private final String firstName;
    private final String lastName;
    private final String password;

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
