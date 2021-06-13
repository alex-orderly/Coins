package me.alexjs.coins.api;

import me.alexjs.coins.api.dto.*;

public interface AccountApi {

    GetBalanceResponse getBalance(String accountId);

    ListTransactionsResponse listTransactions(String accountId);

    CreateDepositResponse createDeposit(String accountId, TransactionRequest request);

    CreateWithdrawalResponse createWithdrawal(String accountId, TransactionRequest request);

}
