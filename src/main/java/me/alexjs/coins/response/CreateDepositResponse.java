package me.alexjs.coins.response;

import org.springframework.http.HttpStatus;

public class CreateDepositResponse extends Response {

    public CreateDepositResponse(HttpStatus responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public CreateDepositResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public CreateDepositResponse(HttpStatus responseCode) {
        super(responseCode);
    }

}
