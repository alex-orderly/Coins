package me.alexjs.coins.api;

import me.alexjs.coins.request.CreateAccountRequest;
import me.alexjs.coins.request.ListAccountsRequest;
import me.alexjs.coins.response.CreateAccountResponse;
import me.alexjs.coins.response.ListAccountsResponse;
import me.alexjs.coins.response.ListTransactionsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AccountApi {

    @PostMapping("/create")
    CreateAccountResponse createAccount(@PathVariable("userId") String shortId,
                                        @RequestBody CreateAccountRequest request);

    @GetMapping("/list")
    ListAccountsResponse listAccounts(@PathVariable("userId") String shortId);

}
