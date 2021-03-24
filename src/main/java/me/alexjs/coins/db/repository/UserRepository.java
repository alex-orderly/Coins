package me.alexjs.coins.db.repository;

import me.alexjs.coins.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUserName(String userName);

}
