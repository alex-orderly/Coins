package me.alexjs.coins.api.dto;

import java.util.List;

public class ListTransactionsResponse {

    private List<TransactionDto> transactions;

    public ListTransactionsResponse() {
    }

    public ListTransactionsResponse(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

}
