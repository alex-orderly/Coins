package me.alexjs.coins.api;

import me.alexjs.coins.request.CreateAccountRequest;
import me.alexjs.coins.request.CreateUserRequest;
import me.alexjs.coins.response.CreateAccountResponse;
import me.alexjs.coins.response.CreateUserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.UUID;

public interface UserApi {

    @PostMapping("/create")
    CreateUserResponse createUser(@RequestBody CreateUserRequest request);

    @PostMapping("/accounts/create")
    CreateAccountResponse createAccount(@PathVariable("userName") String userName,
                                        @RequestBody CreateAccountRequest request);

    @GetMapping("/accounts/list")
    Map<UUID, String> listAccounts(@PathVariable("userName") String userName);

}
