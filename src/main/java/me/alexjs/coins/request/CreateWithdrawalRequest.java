package me.alexjs.coins.request;

public class CreateWithdrawalRequest extends TransactionRequest {

    public CreateWithdrawalRequest(String description, String amount) {
        super(description, amount);
    }

}
