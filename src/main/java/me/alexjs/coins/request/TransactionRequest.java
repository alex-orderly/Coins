package me.alexjs.coins.request;

public abstract class TransactionRequest {

    protected final String description;
    protected final String amount;

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
