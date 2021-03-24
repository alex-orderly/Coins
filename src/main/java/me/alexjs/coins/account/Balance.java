package me.alexjs.coins.account;

import me.alexjs.coins.transaction.Amount;

public class Balance implements Cloneable {

    private long dollars;
    private long cents;

    public Balance() {
        this.dollars = 0;
        this.cents = 0;
    }

    public Balance(long dollars, long cents) {
        this.dollars = dollars;
        this.cents = cents;
    }

    public boolean isNegative() {
        if (dollars < 0) {
            return true;
        }
        return cents < 0;
    }

    public Balance applyDeposit(Amount other) {
        long newDollars = this.dollars + other.getDollars();
        long newCents = this.cents + other.getCents();

        if (newCents >= 100) {
            newDollars++;
            newCents -= 100;
        }

        this.dollars = newDollars;
        this.cents = newCents;

        return this;
    }

    public Balance applyWithdrawal(Amount other) {
        long newDollars = this.dollars - other.getDollars();
        long newCents = this.cents - other.getCents();

        if (newCents < 0) {
            newDollars--;
            newCents += 100;
        }

        this.dollars = newDollars;
        this.cents = newCents;

        return this;
    }

    public long getDollars() {
        return dollars;
    }

    public long getCents() {
        return cents;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
