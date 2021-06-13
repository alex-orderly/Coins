package me.alexjs.coins.api.dto.body;

import java.util.Map;
import java.util.UUID;

public record ListAccountsResponse(Map<UUID, String> accounts) {
}
