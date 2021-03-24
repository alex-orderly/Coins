package me.alexjs.coins.response;

import org.springframework.http.HttpStatus;

public class CreateTransferResponse extends Response {

    public CreateTransferResponse(HttpStatus responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public CreateTransferResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public CreateTransferResponse(HttpStatus responseCode) {
        super(responseCode);
    }

}
