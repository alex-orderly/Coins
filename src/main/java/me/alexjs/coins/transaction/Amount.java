package me.alexjs.coins.transaction;

public class Amount {

    private final long dollars;
    private final long cents;

    public Amount(long dollars, long cents) {
        validateValues(dollars, cents);
        this.dollars = dollars;
        this.cents = cents;
    }

    public Amount add(Amount other) {
        long newDollars = this.dollars + other.dollars;
        long newCents = this.cents + other.cents;
        if (newCents >= 100) {
            newDollars++;
            newCents -= 100;
        }
        return new Amount(newDollars, newCents);
    }

    public Amount subtract(Amount other) {
        long newDollars = this.dollars - other.dollars;
        long newCents = this.cents - other.cents;
        validateValues(newDollars, newCents);
        return new Amount(newDollars, newCents);
    }

    public long getDollars() {
        return dollars;
    }

    public long getCents() {
        return cents;
    }

    private void validateValues(long dollars, long cents) {
        if (dollars < 0 || cents < 0) {
            throw new IllegalArgumentException("Amount values cannot be negative");
        }
        if (cents >= 100) {
            throw new IllegalArgumentException("Cents cannot be greater than or equal to 100");
        }
    }

}
