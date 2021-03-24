package me.alexjs.coins.request;

public class CreateTransferRequest extends TransactionRequest {

    private final String toAccountId;

    public CreateTransferRequest(String toAccountId, String description, String amount) {
        super(description, amount);
        this.toAccountId = toAccountId;
    }

}
