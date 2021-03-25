package me.alexjs.coins.api;

import me.alexjs.coins.db.Transaction;
import me.alexjs.coins.request.TransactionRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

// URI: /api/accounts/{accountId}
public interface AccountApi {

    @GetMapping("/balance")
    BigDecimal getBalance(@PathVariable("accountId") String accountId);

    @GetMapping("/transactions")
    List<Transaction> listTransactions(@PathVariable("accountId") String accountId);

    @PostMapping("/deposits")
    void createDeposit(@PathVariable("accountId") String accountId,
                       @RequestBody TransactionRequest request);

    @PostMapping("/withdrawals")
    void createWithdrawal(@PathVariable("accountId") String accountId,
                          @RequestBody TransactionRequest request);

}
