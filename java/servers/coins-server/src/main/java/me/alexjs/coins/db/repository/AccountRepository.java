package me.alexjs.coins.db.repository;

import me.alexjs.coins.db.Account;
import me.alexjs.coins.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findByUser(User user);

}
