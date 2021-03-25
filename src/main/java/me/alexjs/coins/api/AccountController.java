package me.alexjs.coins.api;

import me.alexjs.coins.db.Account;
import me.alexjs.coins.db.Transaction;
import me.alexjs.coins.db.TransactionType;
import me.alexjs.coins.db.repository.AccountRepository;
import me.alexjs.coins.db.repository.TransactionRepository;
import me.alexjs.coins.request.TransactionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts/{accountId}")
public class AccountController implements AccountApi {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository,
                             TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public BigDecimal getBalance(String accountId) {
        log.info("Received request: GET /balance");

        Account account = accountRepository.getOne(UUID.fromString(accountId));
        var transactions = transactionRepository.findByAccount(account);

        BigDecimal balance = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.DEPOSIT) {
                balance = balance.add(transaction.getAmount());
            } else if (transaction.getType() == TransactionType.WITHDRAWAL) {
                balance = balance.subtract(transaction.getAmount());
            } else {
                // Error
            }
        }

        return balance;
    }

    @Override
    public List<Transaction> listTransactions(String accountId) {
        log.info("Received request: GET /transactions");

        Account account = accountRepository.getOne(UUID.fromString(accountId));
        List<Transaction> transactions = transactionRepository.findByAccount(account);

        return transactions;
    }

    @Override
    public void createDeposit(String accountId, TransactionRequest request) {
        log.info("Received request: POST /deposits");

        Account account = accountRepository.getOne(UUID.fromString(accountId));
        String description = request.getDescription();
        TransactionType type = TransactionType.DEPOSIT;
        BigDecimal amount = convertAmount(request.getAmount());

        Transaction transaction = new Transaction(account, description, type, amount);

        transactionRepository.save(transaction);
    }

    @Override
    public void createWithdrawal(String accountId, TransactionRequest request) {
        log.info("Received request: POST /withdrawals");

        Account account = accountRepository.getOne(UUID.fromString(accountId));
        String description = request.getDescription();
        TransactionType type = TransactionType.WITHDRAWAL;
        BigDecimal amount = convertAmount(request.getAmount());

        Transaction transaction = new Transaction(account, description, type, amount);

        transactionRepository.save(transaction);
    }

    private BigDecimal convertAmount(String amountString) {
        // TODO Make this better
        return new BigDecimal(amountString);
    }

}
