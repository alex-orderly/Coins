package me.alexjs.coins.api;

import me.alexjs.coins.db.Transaction;
import me.alexjs.coins.request.CreateDepositRequest;
import me.alexjs.coins.request.CreateTransferRequest;
import me.alexjs.coins.request.CreateWithdrawalRequest;
import me.alexjs.coins.response.CreateDepositResponse;
import me.alexjs.coins.response.CreateTransferResponse;
import me.alexjs.coins.response.CreateWithdrawalResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TransactionApi {

}
