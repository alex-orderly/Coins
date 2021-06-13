package me.alexjs.coins.api;

import me.alexjs.coins.api.dto.body.*;

public interface UserApi {

    CreateUserResponse createUser(String username, CreateUserRequest request);

    GetUserResponse getUser(String username);

    CreateAccountResponse createAccount(String username, CreateAccountRequest request);

    ListAccountsResponse listAccounts(String username);

}
