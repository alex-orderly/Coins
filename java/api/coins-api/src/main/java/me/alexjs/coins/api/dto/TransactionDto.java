package me.alexjs.coins.api.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class TransactionDto {

    private String type;
    private BigDecimal amount;
    private Instant timestamp;

    public TransactionDto() {
    }

    public TransactionDto(String type, BigDecimal amount, Instant timestamp) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

}
