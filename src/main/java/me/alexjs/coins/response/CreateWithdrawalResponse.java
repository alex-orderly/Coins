package me.alexjs.coins.response;

import org.springframework.http.HttpStatus;

public class CreateWithdrawalResponse extends Response {

    public CreateWithdrawalResponse(HttpStatus responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public CreateWithdrawalResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public CreateWithdrawalResponse(HttpStatus responseCode) {
        super(responseCode);
    }

}
