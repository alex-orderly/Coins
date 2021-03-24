package me.alexjs.coins.account;

import me.alexjs.coins.transaction.Amount;
import me.alexjs.coins.transaction.Transaction;
import me.alexjs.coins.transaction.TransactionType;

import java.util.LinkedList;
import java.util.List;

public class Account {

    private final List<Transaction> transactions;

    private String name;
    private Balance balance;

    public Account(String name) {
        this.name = name;
        this.transactions = new LinkedList<>();
        this.balance = new Balance();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        updateBalance(transaction.getType(), transaction.getAmount());
    }

    public void addTransaction(String description, TransactionType type, Amount amount) {
        this.transactions.add(new Transaction(description, type, amount));
        updateBalance(type, amount);
    }

    public void addTransaction(String description, TransactionType type, long dollars, long cents) {
        Amount amount = new Amount(dollars, cents);
        this.transactions.add(new Transaction(description, type, amount));
        updateBalance(type, amount);
    }

    private void updateBalance(TransactionType type, Amount amount) {
        switch (type) {
            case DEPOSIT:
                balance = balance.applyDeposit(amount);
                break;
            case WITHDRAWAL:
                balance = balance.applyWithdrawal(amount);
                break;
            default:
                break;
        }
    }

    public Balance getBalance() {
        try {
            return (Balance) balance.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
