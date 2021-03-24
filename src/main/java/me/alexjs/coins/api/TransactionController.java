package me.alexjs.coins.api;

import me.alexjs.coins.request.CreateDepositRequest;
import me.alexjs.coins.request.CreateTransferRequest;
import me.alexjs.coins.request.CreateWithdrawalRequest;
import me.alexjs.coins.response.CreateDepositResponse;
import me.alexjs.coins.response.CreateTransferResponse;
import me.alexjs.coins.response.CreateWithdrawalResponse;
import me.alexjs.coins.response.ListTransactionsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/{userId}/accounts/{accountId}/transaction")
public class TransactionController implements TransactionApi {

    Logger log = LoggerFactory.getLogger(TransactionController.class);

    @Override
    public CreateDepositResponse createDeposit(String userId, String accountId, CreateDepositRequest request) {
        log.info("Received request: CreateDepositRequest");

        return new CreateDepositResponse(HttpStatus.OK);
    }

    @Override
    public CreateWithdrawalResponse createWithdrawal(String userId, String accountId, CreateWithdrawalRequest request) {
        log.info("Received request: CreateWithdrawalRequest");

        return new CreateWithdrawalResponse(HttpStatus.OK);
    }

    @Override
    public CreateTransferResponse createTransfer(String userId, String accountId, CreateTransferRequest request) {
        log.info("Received request: CreateTransferRequest");

        return new CreateTransferResponse(HttpStatus.OK);
    }

    @Override
    public ListTransactionsResponse listTransactions(String userId, String accountId) {
        log.info("Received request for listTransactions()");

        return new ListTransactionsResponse(HttpStatus.OK);
    }

}
