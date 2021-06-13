package me.alexjs.coins.api.dto.body;

import me.alexjs.coins.api.dto.TransactionDto;

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
