package me.alexjs.coins.request;

public class TransactionRequest {

    private final String description;
    private final String amount;

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
