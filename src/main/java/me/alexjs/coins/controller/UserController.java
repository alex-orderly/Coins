package me.alexjs.coins.controller;

import me.alexjs.coins.api.UserApi;
import me.alexjs.coins.api.dto.*;
import me.alexjs.coins.api.util.CoinsException;
import me.alexjs.coins.api.util.CoinsResponse;
import me.alexjs.coins.db.Account;
import me.alexjs.coins.db.User;
import me.alexjs.coins.db.repository.AccountRepository;
import me.alexjs.coins.db.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public UserController(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public CreateUserResponse createUser(String username, CreateUserRequest request) {

        String passwordHash = hashAndEncode(request.getPassword());

        if (userRepository.existsByUsername(username)) {
            throw new CoinsException(CoinsResponse.USERNAME_TAKEN, username);
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
    public CreateAccountResponse createAccount(String username, CreateAccountRequest request) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CoinsException(CoinsResponse.INVALID_USER, username);
        }

        String name = request.getName();
        Account account = new Account(user, name);

        accountRepository.save(account);

        return new CreateAccountResponse(account.getId(), account.getName());

    }

    @Override
    public ListAccountsResponse listAccounts(String username) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CoinsException(CoinsResponse.INVALID_USER, username);
        }

        List<Account> accounts = accountRepository.findByUser(user);

        Map<UUID, String> accountMap = new HashMap<>();
        for (Account account : accounts) {
            accountMap.put(account.getId(), account.getName());
        }

        return new ListAccountsResponse(accountMap);

    }

    private String hashAndEncode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
