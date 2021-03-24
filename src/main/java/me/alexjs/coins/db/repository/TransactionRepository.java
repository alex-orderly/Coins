package me.alexjs.coins.db.repository;

import me.alexjs.coins.db.Account;
import me.alexjs.coins.db.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByAccount(Account account);

}
