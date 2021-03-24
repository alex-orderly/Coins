package me.alexjs.coins.request;

public class CreateUserRequest {

    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String base64HashedPassword;

    public CreateUserRequest(String firstName, String lastName, String userName, String base64HashedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.base64HashedPassword = base64HashedPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getBase64HashedPassword() {
        return base64HashedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
