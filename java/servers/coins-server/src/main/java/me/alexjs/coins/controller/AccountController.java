package me.alexjs.coins.controller;

import me.alexjs.coins.api.AccountApi;
import me.alexjs.coins.api.dto.*;
import me.alexjs.coins.api.dto.body.*;
import me.alexjs.coins.db.Account;
import me.alexjs.coins.db.Transaction;
import me.alexjs.coins.db.TransactionType;
import me.alexjs.coins.db.repository.AccountRepository;
import me.alexjs.coins.db.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
    @GetMapping("/balance")
    public GetBalanceResponse getBalance(@PathVariable("accountId") String accountId) {

        Account account = getAccountById(accountId);
        try {
            return new GetBalanceResponse(transactionRepository.findTopByAccountOrderByAudit_CreatedAtDesc(account).getTotal());
        } catch (NullPointerException e) {
            return new GetBalanceResponse(BigDecimal.ZERO);
        }

    }

    @Override
    @GetMapping("/transactions")
    public ListTransactionsResponse listTransactions(@PathVariable("accountId") String accountId) {

        Account account = getAccountById(accountId);
        List<Transaction> transactions = transactionRepository.findByAccount(account);

        List<TransactionDto> dtos = new ArrayList<>();
        for(var transaction : transactions) {
            dtos.add(new TransactionDto(transaction.getType().getLabel(), transaction.getAmount(), transaction.getAudit().getCreatedAt().toInstant()));
        }
        dtos.sort(Comparator.comparing(TransactionDto::timestamp));

        return new ListTransactionsResponse(dtos);

    }

    @Override
    @PostMapping("/deposits")
    public CreateDepositResponse createDeposit(@PathVariable("accountId") String accountId,
                                               @RequestBody TransactionRequest request) {

        createTransaction(accountId, request, TransactionType.DEPOSIT);
        return new CreateDepositResponse();

    }

    @Override
    @PostMapping("/withdrawals")
    public CreateWithdrawalResponse createWithdrawal(@PathVariable("accountId") String accountId,
                                                     @RequestBody TransactionRequest request) {

        createTransaction(accountId, request, TransactionType.WITHDRAWAL);
        return new CreateWithdrawalResponse();

    }

    private void createTransaction(String accountId, TransactionRequest request, TransactionType type) {

        Account account = getAccountById(accountId);

        String description = request.description();
        BigDecimal amount = convertAmount(request.amount());
        BigDecimal oldBalance = getBalance(accountId).balance();

        Transaction transaction = new Transaction(account, description, type, amount, oldBalance);
        transaction.updateTotal(type, amount);

        transactionRepository.save(transaction);

        updateRunningTotalsAfter(account, transaction);

    }

    private Account getAccountById(String accountId) {
        try {
            return accountRepository.findById(UUID.fromString(accountId)).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("This account does not exist: " + accountId);
        }
    }

    private void updateRunningTotalsAfter(Account account, Transaction transaction) {

        List<Transaction> transactionsToUpdate;
        try {
            transactionsToUpdate = transactionRepository.findTransactionsAfterByAccount(account, transaction.getAudit().getCreatedAt());
        } catch (InvalidDataAccessResourceUsageException e) {
            return;
        }

        for (Transaction newer : transactionsToUpdate) {
            newer.updateTotal(transaction.getType(), transaction.getAmount());
            transactionRepository.save(newer);
        }

    }

    private BigDecimal convertAmount(String amountString) {
        return new BigDecimal(amountString).setScale(6, RoundingMode.HALF_UP);
    }

}
