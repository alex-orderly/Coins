package me.alexjs.coins;

import me.alexjs.coins.api.AccountApi;
import me.alexjs.coins.api.UserApi;
import me.alexjs.coins.api.dto.body.CreateAccountRequest;
import me.alexjs.coins.api.dto.body.CreateUserRequest;

import java.util.UUID;

public abstract class CoinsTest {

    protected static final String FIRST_NAME = "Alex";
    protected static final String LAST_NAME = "Sweeney";
    protected static final String USERNAME = "ajs1998";
    protected static final String PASSWORD = "password1";
    protected static final String ACCOUNT_NAME_1 = "My test account";
    protected static final String ACCOUNT_NAME_2 = "My other account";

    protected static final double AMOUNT_1 = 423.19;
    protected static final double AMOUNT_2 = 45.67;
    protected static final double AMOUNT_3 = 50.00;

    protected final UserApi userClient;
    protected final AccountApi accountClient;

    public CoinsTest(UserApi userClient, AccountApi accountClient) {
        this.userClient = userClient;
        this.accountClient = accountClient;
    }

    protected UUID createAccount() {
        CreateUserRequest userRequest = new CreateUserRequest(FIRST_NAME, LAST_NAME, PASSWORD);
        userClient.createUser(USERNAME, userRequest);
        CreateAccountRequest accountRequest = new CreateAccountRequest(ACCOUNT_NAME_1);
        return userClient.createAccount(USERNAME, accountRequest).id();
    }

}
