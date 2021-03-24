package me.alexjs.coins.api;

import me.alexjs.coins.db.Transaction;
import me.alexjs.coins.request.CreateAccountRequest;
import me.alexjs.coins.request.CreateDepositRequest;
import me.alexjs.coins.request.CreateTransferRequest;
import me.alexjs.coins.request.CreateWithdrawalRequest;
import me.alexjs.coins.response.CreateAccountResponse;
import me.alexjs.coins.response.CreateDepositResponse;
import me.alexjs.coins.response.CreateTransferResponse;
import me.alexjs.coins.response.CreateWithdrawalResponse;
import me.alexjs.coins.transaction.Amount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface AccountApi {

    @GetMapping("/balance")
    Amount getBalance(@PathVariable("accountId") String accountId);

    @PostMapping("/deposit")
    CreateDepositResponse createDeposit(@PathVariable("accountId") String accountId,
                                        @RequestBody CreateDepositRequest request);

    @PostMapping("/withdraw")
    CreateWithdrawalResponse createWithdrawal(@PathVariable("accountId") String accountId,
                                              @RequestBody CreateWithdrawalRequest request);

    @PostMapping("/transfer")
    CreateTransferResponse createTransfer(@PathVariable("accountId") String accountId,
                                          @RequestBody CreateTransferRequest request);

    @GetMapping("/transactions")
    List<Transaction> listTransactions(@PathVariable("accountId") String accountId);

}
