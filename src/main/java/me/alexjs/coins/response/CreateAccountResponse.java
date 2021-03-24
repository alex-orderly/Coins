package me.alexjs.coins.response;

import org.springframework.http.HttpStatus;

public class CreateAccountResponse extends Response {

    public CreateAccountResponse(HttpStatus responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public CreateAccountResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public CreateAccountResponse(HttpStatus responseCode) {
        super(responseCode);
    }

}
