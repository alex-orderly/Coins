package me.alexjs.coins.request;

public class CreateDepositRequest extends TransactionRequest {

    public CreateDepositRequest(String description, String amount) {
        super(description, amount);
    }

}
