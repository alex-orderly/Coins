package me.alexjs.coins.api;

import me.alexjs.coins.db.Account;
import me.alexjs.coins.db.User;
import me.alexjs.coins.db.repository.AccountRepository;
import me.alexjs.coins.db.repository.UserRepository;
import me.alexjs.coins.request.CreateAccountRequest;
import me.alexjs.coins.request.CreateUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    @Autowired
    public UserController(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void createUser(CreateUserRequest request) {
        log.info("Received request: POST /create");

        // TODO actually hash the password
        String passwordHash = request.getPassword();

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                passwordHash);

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CoinsException(HttpStatus.BAD_REQUEST, "Could not create user: This username is taken");
        }

        userRepository.save(user);
    }

    @Override
    public Map<UUID, String> createAccount(String username, CreateAccountRequest request) {
        log.info("Received request: POST /accounts");

        User user = userRepository.findByUsername(username);
        String name = request.getName();
        Account account = new Account(user, name);

        accountRepository.save(account);

        return Map.of(account.getId(), name);
    }

    @Override
    public Map<UUID, String> listAccounts(String username) {
        log.info("Received request: GET /accounts");

        User user = userRepository.findByUsername(username);
        List<Account> accounts = accountRepository.findByUser(user);

        Map<UUID, String> accountMap = accounts.stream().collect(Collectors.toMap(Account::getId, Account::getName));

        return accountMap;
    }

}
