package me.alexjs.coins.controller;

import me.alexjs.coins.api.AccountApi;
import me.alexjs.coins.api.dto.*;
import me.alexjs.coins.api.util.CoinsException;
import me.alexjs.coins.api.util.CoinsResponse;
import me.alexjs.coins.db.Account;
import me.alexjs.coins.db.Transaction;
import me.alexjs.coins.db.TransactionType;
import me.alexjs.coins.db.repository.AccountRepository;
import me.alexjs.coins.db.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
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
    public GetBalanceResponse getBalance(String accountId) {
        Account account = getAccountById(accountId);
        try {
            return new GetBalanceResponse(transactionRepository.findTopByAccountOrderByAudit_CreatedAtDesc(account).getTotal());
        } catch (NullPointerException e) {
            return new GetBalanceResponse(BigDecimal.ZERO);
        }
    }

    @Override
    public ListTransactionsResponse listTransactions(String accountId) {
        Account account = getAccountById(accountId);
        return new ListTransactionsResponse(transactionRepository.findByAccount(account));
    }

    @Override
    public CreateDepositResponse createDeposit(String accountId, TransactionRequest request) {
        createTransaction(accountId, request, TransactionType.DEPOSIT);
        return new CreateDepositResponse();
    }

    @Override
    public CreateWithdrawalResponse createWithdrawal(String accountId, TransactionRequest request) {
        createTransaction(accountId, request, TransactionType.WITHDRAWAL);
        return new CreateWithdrawalResponse();
    }

    private void createTransaction(String accountId, TransactionRequest request, TransactionType type) {

        Account account = getAccountById(accountId);

        String description = request.getDescription();
        BigDecimal amount = convertAmount(request.getAmount());
        BigDecimal oldBalance = getBalance(accountId).getBalance();

        Transaction transaction = new Transaction(account, description, type, amount, oldBalance);
        transaction.updateTotal(type, amount);

        transactionRepository.save(transaction);

        updateRunningTotalsAfter(account, transaction);

    }

    private Account getAccountById(String accountId) {

        try {
            return accountRepository.findById(UUID.fromString(accountId)).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new CoinsException(CoinsResponse.INVALID_ACCOUNT, accountId);
        }

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
