package me.alexjs.coins.response;

import org.springframework.http.HttpStatus;

public abstract class Response {

    protected final HttpStatus responseCode;
    protected final String responseMessage;
    private final long timestamp;

    public Response(HttpStatus responseCode, String responseMessage) {
        this.timestamp = System.currentTimeMillis() / 1000L;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public Response(int responseCode, String responseMessage) {
        this(HttpStatus.resolve(responseCode), responseMessage);
    }

    public Response(HttpStatus responseCode) {
        this(responseCode, responseCode.getReasonPhrase());
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getResponseCode() {
        return responseCode.value();
    }

    public String getResponseMessage() {
        return responseMessage;
    }

}
