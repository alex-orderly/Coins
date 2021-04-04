package me.alexjs.coins.api.util;

import org.springframework.http.HttpStatus;

public enum CoinsResponse {

    BAD_AUTH(HttpStatus.UNAUTHORIZED, "Invalid Authentication"),
    INVALID_USER(HttpStatus.BAD_REQUEST, "This user does not exist: "),
    INVALID_ACCOUNT(HttpStatus.BAD_REQUEST, "This account does not exist: "),
    USERNAME_TAKEN(HttpStatus.BAD_REQUEST, "This username is taken: ");

    private final HttpStatus status;
    private final String message;

    CoinsResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
