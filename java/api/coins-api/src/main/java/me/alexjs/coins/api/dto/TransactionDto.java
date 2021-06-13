package me.alexjs.coins.api.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionDto(String type, BigDecimal amount, Instant timestamp) {
}
