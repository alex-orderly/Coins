package me.alexjs.coins.controller;

import me.alexjs.coins.api.UserApi;
import me.alexjs.coins.api.dto.body.*;
import me.alexjs.coins.db.Account;
import me.alexjs.coins.db.User;
import me.alexjs.coins.db.repository.AccountRepository;
import me.alexjs.coins.db.repository.UserRepository;
import me.alexjs.coins.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordUtil passwordUtil;

    @Autowired
    public UserController(UserRepository userRepository, AccountRepository accountRepository, PasswordUtil passwordUtil) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.passwordUtil = passwordUtil;
    }

    @Override
    @PostMapping("{username}")
    public CreateUserResponse createUser(@PathVariable("username") String username,
                                         @RequestBody CreateUserRequest request) {

        String passwordHash = passwordUtil.hashAndEncode(request.getPassword());

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("This username is taken: " + username);
        }

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                username,
                passwordHash);

        userRepository.save(user);

        return new CreateUserResponse(user.getUsername(), user.getFirstName(), user.getLastName());

    }

    @Override
    @GetMapping("{username}")
    public GetUserResponse getUser(@PathVariable("username") String username) {
        User user = userRepository.findByUsername(username);
        return new GetUserResponse(user.getUsername(), user.getFirstName(), user.getLastName(), user.getPasswordHash());
    }

    @Override
    @PostMapping("{username}/accounts")
    public CreateAccountResponse createAccount(@PathVariable("username") String username,
                                               @RequestBody CreateAccountRequest request) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("This user does not exist: " + username);
        }

        String name = request.getName();
        Account account = new Account(user, name);

        accountRepository.save(account);

        return new CreateAccountResponse(account.getId(), account.getName());

    }

    @Override
    @GetMapping("{username}/accounts")
    public ListAccountsResponse listAccounts(@PathVariable("username") String username) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("This user does not exist: " + username);
        }

        List<Account> accounts = accountRepository.findByUser(user);

        Map<UUID, String> accountMap = new HashMap<>();
        for (Account account : accounts) {
            accountMap.put(account.getId(), account.getName());
        }

        return new ListAccountsResponse(accountMap);

    }

}
