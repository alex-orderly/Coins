package me.alexjs.coins.api.dto.body;

import java.math.BigDecimal;

public record GetBalanceResponse(BigDecimal balance) {
}
