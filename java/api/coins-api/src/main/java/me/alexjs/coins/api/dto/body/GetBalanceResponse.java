package me.alexjs.coins.api.dto.body;

import java.math.BigDecimal;

public class GetBalanceResponse {

    private BigDecimal balance;

    public GetBalanceResponse() {
    }

    public GetBalanceResponse(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

}
