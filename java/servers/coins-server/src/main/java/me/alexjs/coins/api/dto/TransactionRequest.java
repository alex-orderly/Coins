package me.alexjs.coins.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class TransactionRequest {

    private String description;
    private String amount;

    public TransactionRequest() {
    }

    @JsonCreator
    public TransactionRequest(String description, String amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public String getAmount() {
        return amount;
    }

}
