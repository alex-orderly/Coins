package me.alexjs.coins.api;

import me.alexjs.coins.api.dto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// URI: /api/accounts/{accountId}
public interface AccountApi {

    @GetMapping("/balance")
    GetBalanceResponse getBalance(@PathVariable("accountId") String accountId);

    @GetMapping("/transactions")
    ListTransactionsResponse listTransactions(@PathVariable("accountId") String accountId);

    @PostMapping("/deposits")
    CreateDepositResponse createDeposit(@PathVariable("accountId") String accountId,
                                        @RequestBody TransactionRequest request);

    @PostMapping("/withdrawals")
    CreateWithdrawalResponse createWithdrawal(@PathVariable("accountId") String accountId,
                                              @RequestBody TransactionRequest request);

}
