package me.alexjs.coins.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;

public class GetBalanceResponse {

    private BigDecimal balance;

    public GetBalanceResponse() {
    }

    @JsonCreator
    public GetBalanceResponse(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

}
