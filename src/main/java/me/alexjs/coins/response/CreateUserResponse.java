package me.alexjs.coins.response;

import org.springframework.http.HttpStatus;

public class CreateUserResponse extends Response {

    public CreateUserResponse(HttpStatus responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public CreateUserResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public CreateUserResponse(HttpStatus responseCode) {
        super(responseCode);
    }

}
