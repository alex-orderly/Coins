package me.alexjs.coins.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import me.alexjs.coins.db.Transaction;

import java.util.List;

public class ListTransactionsResponse {

    private List<Transaction> transactions;

    public ListTransactionsResponse() {
    }

    @JsonCreator
    public ListTransactionsResponse(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

}
