package me.alexjs.coins.response;

import org.springframework.http.HttpStatus;

public class ListAccountsResponse extends Response {

    public ListAccountsResponse(HttpStatus responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public ListAccountsResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public ListAccountsResponse(HttpStatus responseCode) {
        super(responseCode);
    }

}
