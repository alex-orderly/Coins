package me.alexjs.coins.api.dto.body;

import java.util.UUID;

public record CreateAccountResponse(UUID id, String name) {
}
