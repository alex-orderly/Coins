package me.alexjs.coins.api;

import me.alexjs.coins.db.AccountDbo;
import me.alexjs.coins.db.UserDbo;
import me.alexjs.coins.request.CreateAccountRequest;
import me.alexjs.coins.request.ListAccountsRequest;
import me.alexjs.coins.response.CreateAccountResponse;
import me.alexjs.coins.response.ListAccountsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/accounts")
public class AccountController implements AccountApi {

    Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public CreateAccountResponse createAccount(String shortId, CreateAccountRequest request) {
        log.info("Received request: CreateAccountRequest");

        String name = request.getName();
        AccountDbo account = new AccountDbo(name);

//        session.persist(account);

        return new CreateAccountResponse(HttpStatus.OK);
    }

    @Override
    public ListAccountsResponse listAccounts(String shortId) {
        log.info("Received request: ListAccountsRequest");

//        UUID userId = UserDbo.getIdFromShortId(shortId);
//
//        AccountDbo.countUserAccounts(userId);

        return new ListAccountsResponse(HttpStatus.OK);
    }

}
