package me.alexjs.coins.api;

import me.alexjs.coins.request.CreateAccountRequest;
import me.alexjs.coins.request.CreateUserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

// URI: /api/users
public interface UserApi {

    @PostMapping
    void createUser(@RequestBody CreateUserRequest request);

    @PostMapping("{username}/accounts")
    Map<UUID, String> createAccount(@PathVariable("username") String username,
                             @RequestBody CreateAccountRequest request);

    @GetMapping("{username}/accounts")
    Map<UUID, String> listAccounts(@PathVariable("username") String username);

}
