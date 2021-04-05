package me.alexjs.coins.api;

import me.alexjs.coins.db.User;
import me.alexjs.coins.api.request.CreateAccountRequest;
import me.alexjs.coins.api.request.CreateUserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

// URI: /api/users
public interface UserApi {

    @PostMapping("{username}")
    User createUser(@PathVariable("username") String username,
                    @RequestBody CreateUserRequest request);

    @PostMapping("{username}/accounts")
    Map<UUID, String> createAccount(@PathVariable("username") String username,
                             @RequestBody CreateAccountRequest request);

    @GetMapping("{username}/accounts")
    Map<UUID, String> listAccounts(@PathVariable("username") String username);

}
