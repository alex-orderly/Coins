package me.alexjs.coins.api;

import me.alexjs.coins.api.util.CoinsException;
import me.alexjs.coins.api.util.CoinsResponse;
import me.alexjs.coins.db.Account;
import me.alexjs.coins.db.User;
import me.alexjs.coins.db.repository.AccountRepository;
import me.alexjs.coins.db.repository.UserRepository;
import me.alexjs.coins.request.CreateAccountRequest;
import me.alexjs.coins.request.CreateUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    public User createUser(CreateUserRequest request) {
        log.info("Received request: POST /create");

        // TODO actually hash the password
        String passwordHash = hashAndEncode(request.getPassword());

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                passwordHash);

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CoinsException(CoinsResponse.USERNAME_TAKEN, request.getUsername());
        }

        userRepository.save(user);

        return user;
    }

    @Override
    public Map<UUID, String> createAccount(String username, CreateAccountRequest request) {
        log.info("Received request: POST /accounts");

        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new CoinsException(CoinsResponse.INVALID_USER, username);
        }

        String name = request.getName();
        Account account = new Account(user, name);

        accountRepository.save(account);

        return Map.of(account.getId(), name);
    }

    @Override
    public Map<UUID, String> listAccounts(String username) {
        log.info("Received request: GET /accounts");

        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new CoinsException(CoinsResponse.INVALID_USER, username);
        }

        List<Account> accounts = accountRepository.findByUser(user);

        Map<UUID, String> accountMap = accounts.stream().collect(Collectors.toMap(Account::getId, Account::getName));

        return accountMap;
    }

    private String hashAndEncode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
