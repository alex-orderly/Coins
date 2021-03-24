package me.alexjs.coins.transaction;

public enum TransactionType {

    DEPOSIT("deposit"),
    WITHDRAWAL("withdraw");

    private final String name;

    TransactionType(String name) {
        this.name = name;
    }

    public static TransactionType fromName(String name) {
        for (TransactionType type : TransactionType.values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

}
