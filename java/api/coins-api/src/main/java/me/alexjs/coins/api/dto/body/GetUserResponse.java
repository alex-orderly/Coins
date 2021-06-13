package me.alexjs.coins.api.dto.body;

public record GetUserResponse(String username, String firstName, String lastName, String passwordHash) {
}
