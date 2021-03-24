package me.alexjs.coins.api;

import me.alexjs.coins.db.Account;
import me.alexjs.coins.db.User;
import me.alexjs.coins.db.repository.AccountRepository;
import me.alexjs.coins.db.repository.UserRepository;
import me.alexjs.coins.request.CreateAccountRequest;
import me.alexjs.coins.request.CreateUserRequest;
import me.alexjs.coins.response.CreateAccountResponse;
import me.alexjs.coins.response.CreateUserResponse;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        log.info("Received request: CreateUserRequest");

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getUserName(),
                request.getBase64HashedPassword());

        userRepository.save(user);

        return new CreateUserResponse(HttpStatus.OK);
    }

    @Override
    public CreateAccountResponse createAccount(String userName, CreateAccountRequest request) {
        log.info("Received request: CreateAccountRequest");

        User user = userRepository.findByUserName(userName);
        String name = request.getName();
        Account account = new Account(user, name);

        accountRepository.save(account);

        return new CreateAccountResponse(HttpStatus.OK);
    }

    @Override
    public Map<UUID, String> listAccounts(String userName) {
        log.info("Received request: ListAccountsRequest");

        User user = userRepository.findByUserName(userName);
        List<Account> accounts = accountRepository.findByUser(user);

        Map<UUID, String> accountMap = accounts.stream().collect(Collectors.toMap(Account::getId, Account::getName));

        return accountMap;
    }


}
