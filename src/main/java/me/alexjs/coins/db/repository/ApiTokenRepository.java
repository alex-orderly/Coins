package me.alexjs.coins.db.repository;

import me.alexjs.coins.db.ApiToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApiTokenRepository extends JpaRepository<ApiToken, UUID> {
}
