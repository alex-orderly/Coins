package me.alexjs.coins.db;

public enum TransactionType {

    DEPOSIT("D"),
    WITHDRAWAL("W");

    private final String label;

    TransactionType(String name) {
        this.label = name;
    }

    public static TransactionType fromLabel(String name) {
        for (TransactionType type : TransactionType.values()) {
            if (type.getLabel().equals(name)) {
                return type;
            }
        }
        return null;
    }

    public String getLabel() {
        return label;
    }

}
