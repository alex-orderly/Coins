package me.alexjs.coins.api.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CoinsException extends RuntimeException {

    private final HttpStatus status;

    public CoinsException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public CoinsException(CoinsResponse response) {
        this(response.getStatus(), response.getMessage());
    }

    public CoinsException(CoinsResponse response, String appendParameter) {
        this(response.getStatus(), response.getMessage() + appendParameter);
    }

    public HttpStatus getStatus() {
        return status;
    }

}
