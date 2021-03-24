package me.alexjs.coins.api;

import me.alexjs.coins.db.Account;
import me.alexjs.coins.db.Transaction;
import me.alexjs.coins.db.User;
import me.alexjs.coins.db.repository.AccountRepository;
import me.alexjs.coins.db.repository.TransactionRepository;
import me.alexjs.coins.db.repository.UserRepository;
import me.alexjs.coins.request.CreateAccountRequest;
import me.alexjs.coins.request.CreateDepositRequest;
import me.alexjs.coins.request.CreateTransferRequest;
import me.alexjs.coins.request.CreateWithdrawalRequest;
import me.alexjs.coins.response.CreateAccountResponse;
import me.alexjs.coins.response.CreateDepositResponse;
import me.alexjs.coins.response.CreateTransferResponse;
import me.alexjs.coins.response.CreateWithdrawalResponse;
import me.alexjs.coins.transaction.Amount;
import me.alexjs.coins.transaction.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts/{accountId}")
public class AccountController implements AccountApi {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Amount getBalance(String accountId) {
        log.info("Received request: GetBalanceRequest");

        Account account = accountRepository.getOne(UUID.fromString(accountId));
        // TODO

    }

    @Override
    public CreateDepositResponse createDeposit(String accountId, CreateDepositRequest request) {
        log.info("Received request: CreateDepositRequest");

        Account account = accountRepository.getOne(UUID.fromString(accountId));
        String description = request.getDescription();
        TransactionType type = TransactionType.DEPOSIT;
        Amount amount = new Amount(request.getAmount());

        Transaction transaction = new Transaction(account, description, type, amount);

        transactionRepository.save(transaction);

        return new CreateDepositResponse(HttpStatus.OK);
    }

    @Override
    public CreateWithdrawalResponse createWithdrawal(String accountId, CreateWithdrawalRequest request) {
        log.info("Received request: CreateWithdrawalRequest");

        Account account = accountRepository.getOne(UUID.fromString(accountId));
        String description = request.getDescription();
        TransactionType type = TransactionType.WITHDRAWAL;
        Amount amount = new Amount(request.getAmount());

        Transaction transaction = new Transaction(account, description, type, amount);

        transactionRepository.save(transaction);

        return new CreateWithdrawalResponse(HttpStatus.OK);
    }

    @Override
    public CreateTransferResponse createTransfer(String accountId, CreateTransferRequest request) {
        log.info("Received request: CreateTransferRequest");

        // TODO

        return new CreateTransferResponse(HttpStatus.OK);
    }

    @Override
    public List<Transaction> listTransactions(String accountId) {
        log.info("Received request for listTransactions()");

        Account account = accountRepository.getOne(UUID.fromString(accountId));
        List<Transaction> transactions = transactionRepository.findByAccount(account);

        return transactions;
    }

}
