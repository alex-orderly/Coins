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
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;
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

        Account account;
        try {
            account = accountRepository.findById(UUID.fromString(accountId)).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new CoinsException(HttpStatus.BAD_REQUEST, "This account does not exist: " + accountId);
        }

        BigDecimal balance;
        try {
            balance = transactionRepository.findTopByAccountOrderByAudit_CreatedAtDesc(account).getTotal();
        } catch (NullPointerException e) {
            return BigDecimal.ZERO;
        }

        return balance;
    }

    @Override
    public List<Transaction> listTransactions(String accountId) {
        log.info("Received request: GET /transactions");

        Account account;
        try {
            account = accountRepository.findById(UUID.fromString(accountId)).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new CoinsException(HttpStatus.BAD_REQUEST, "This account does not exist: " + accountId);
        }

        List<Transaction> transactions = transactionRepository.findByAccount(account);

        return transactions;
    }

    @Override
    public void createDeposit(String accountId, TransactionRequest request) {
        log.info("Received request: POST /deposits");

        Account account;
        try {
            account = accountRepository.findById(UUID.fromString(accountId)).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new CoinsException(HttpStatus.BAD_REQUEST, "This account does not exist: " + accountId);
        }

        String description = request.getDescription();
        TransactionType type = TransactionType.DEPOSIT;
        BigDecimal amount = convertAmount(request.getAmount());
        BigDecimal oldBalance = getBalance(accountId);

        Transaction transaction = new Transaction(account, description, type, amount, oldBalance);
        transaction.updateTotal(TransactionType.DEPOSIT, amount);

        transactionRepository.save(transaction);

        updateRunningTotalsAfter(account, transaction);
    }

    @Override
    public void createWithdrawal(String accountId, TransactionRequest request) {
        log.info("Received request: POST /withdrawals");

        Account account;
        try {
            account = accountRepository.findById(UUID.fromString(accountId)).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new CoinsException(HttpStatus.BAD_REQUEST, "This account does not exist: " + accountId);
        }

        String description = request.getDescription();
        TransactionType type = TransactionType.WITHDRAWAL;
        BigDecimal amount = convertAmount(request.getAmount());
        BigDecimal oldBalance = getBalance(accountId);

        Transaction transaction = new Transaction(account, description, type, amount, oldBalance);
        transaction.updateTotal(TransactionType.WITHDRAWAL, amount);

        transactionRepository.save(transaction);

        updateRunningTotalsAfter(account, transaction);
    }

    private void updateRunningTotalsAfter(Account account, Transaction transaction) {
        List<Transaction> transactionsToUpdate;
        try {
            transactionsToUpdate = transactionRepository.findTransactionsAfterByAccount(account, transaction.getAudit().getCreatedAt());
        } catch (InvalidDataAccessResourceUsageException e) {
            return;
        }

        for(Transaction newer : transactionsToUpdate) {
            newer.updateTotal(transaction.getType(), transaction.getAmount());
            transactionRepository.save(newer);
        }
    }

    private BigDecimal convertAmount(String amountString) {
        return new BigDecimal(amountString).setScale(6, RoundingMode.HALF_UP);
    }

}
