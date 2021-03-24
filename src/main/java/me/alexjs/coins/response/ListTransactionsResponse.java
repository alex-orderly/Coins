package me.alexjs.coins.response;

import org.springframework.http.HttpStatus;

public class ListTransactionsResponse extends Response {

    public ListTransactionsResponse(HttpStatus responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public ListTransactionsResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public ListTransactionsResponse(HttpStatus responseCode) {
        super(responseCode);
    }

}
