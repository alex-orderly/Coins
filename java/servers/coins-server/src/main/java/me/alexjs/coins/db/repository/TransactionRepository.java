package me.alexjs.coins.db.repository;

import me.alexjs.coins.db.Account;
import me.alexjs.coins.db.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByAccount(Account account);

    @Query("SELECT t " +
           "FROM Transaction AS t LEFT JOIN Account AS a " +
           "WHERE ?1 = t.account " +
           "AND ?2 > t.audit.createdAt " +
           "ORDER BY t.audit.createdAt ASC")
    List<Transaction> findTransactionsAfterByAccount(Account account, Timestamp time);

    Transaction findTopByAccountOrderByAudit_CreatedAtDesc(Account account);

}
