package me.alexjs.coins.transaction;

public class Transaction {

    private final String description;
    private final TransactionType type;
    private final Amount amount;

    public Transaction(String description, TransactionType type, long dollars, long cents) {
        this.description = description;
        this.type = type;
        this.amount = new Amount(dollars, cents);
    }

    public Transaction(String description, TransactionType type, Amount amount) {
        this.description = description;
        this.type = type;
        this.amount = amount;
    }

    public static Transaction createDeposit(String name, long dollars, long cents) {
        return createDeposit(name, new Amount(dollars, cents));
    }

    public static Transaction createDeposit(String name, Amount amount) {
        return new Transaction(name, TransactionType.DEPOSIT, amount);
    }

    public static Transaction createWithdrawal(String name, long dollars, long cents) {
        return createWithdrawal(name, new Amount(dollars, cents));
    }

    public static Transaction createWithdrawal(String name, Amount amount) {
        return new Transaction(name, TransactionType.WITHDRAWAL, amount);
    }

    public String getDescription() {
        return description;
    }

    public TransactionType getType() {
        return type;
    }

    public Amount getAmount() {
        return amount;
    }

}
