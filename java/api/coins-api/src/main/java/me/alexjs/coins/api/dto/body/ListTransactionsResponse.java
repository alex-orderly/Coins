package me.alexjs.coins.api.dto.body;

import me.alexjs.coins.api.dto.TransactionDto;

import java.util.List;

public record ListTransactionsResponse(List<TransactionDto> transactions) {
}
