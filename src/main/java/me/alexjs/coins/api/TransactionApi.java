package me.alexjs.coins.api;

import me.alexjs.coins.request.CreateDepositRequest;
import me.alexjs.coins.request.CreateTransferRequest;
import me.alexjs.coins.request.CreateWithdrawalRequest;
import me.alexjs.coins.response.CreateDepositResponse;
import me.alexjs.coins.response.CreateTransferResponse;
import me.alexjs.coins.response.CreateWithdrawalResponse;
import me.alexjs.coins.response.ListTransactionsResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransactionApi {

    @PostMapping("/deposit")
    CreateDepositResponse createDeposit(@PathVariable("userId") String userId,
                                        @PathVariable("accountId") String accountId,
                                        @RequestBody CreateDepositRequest request);

    @PostMapping("/withdraw")
    CreateWithdrawalResponse createWithdrawal(@PathVariable("userId") String userId,
                                              @PathVariable("accountId") String accountId,
                                              @RequestBody CreateWithdrawalRequest request);

    @PostMapping("/transfer")
    CreateTransferResponse createTransfer(@PathVariable("userId") String userId,
                                          @PathVariable("accountId") String accountId,
                                          @RequestBody CreateTransferRequest request);

    @PostMapping("/list")
    ListTransactionsResponse listTransactions(@PathVariable("userId") String userId,
                                              @PathVariable("accountId") String accountId);

}
