package me.alexjs.coins.api;

import me.alexjs.coins.api.dto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// URI: /api/users
public interface UserApi {

    @PostMapping("{username}")
    CreateUserResponse createUser(@PathVariable("username") String username,
                                  @RequestBody CreateUserRequest request);

    @PostMapping("{username}/accounts")
    CreateAccountResponse createAccount(@PathVariable("username") String username,
                                        @RequestBody CreateAccountRequest request);

    @GetMapping("{username}/accounts")
    ListAccountsResponse listAccounts(@PathVariable("username") String username);

}
