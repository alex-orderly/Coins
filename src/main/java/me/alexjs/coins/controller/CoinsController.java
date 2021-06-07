package me.alexjs.coins.controller;

import me.alexjs.coins.api.CoinsApi;
import me.alexjs.coins.api.util.CoinsException;
import me.alexjs.coins.api.util.CoinsResponse;
import me.alexjs.coins.db.ApiToken;
import me.alexjs.coins.db.User;
import me.alexjs.coins.db.repository.AccountRepository;
import me.alexjs.coins.db.repository.ApiTokenRepository;
import me.alexjs.coins.db.repository.TransactionRepository;
import me.alexjs.coins.db.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CoinsController implements CoinsApi {

    private static final Logger log = LoggerFactory.getLogger(CoinsController.class);

    private static final long TOKEN_EXPIRE_SECONDS = 60 * 60 * 24; // 1 DAY

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ApiTokenRepository apiTokenRepository;

    @Autowired
    public CoinsController(UserRepository userRepository,
                           AccountRepository accountRepository,
                           TransactionRepository transactionRepository,
                           ApiTokenRepository apiTokenRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.apiTokenRepository = apiTokenRepository;
    }

    @Override
    public String createToken(Principal principal) {

        String username = principal.getName();

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CoinsException(CoinsResponse.BAD_AUTH);
        }

        String apiTokenString = UUID.randomUUID().toString();
        ApiToken apiToken = new ApiToken(user, apiTokenString, Timestamp.from(Instant.now().plusSeconds(TOKEN_EXPIRE_SECONDS)));

        apiTokenRepository.save(apiToken);

        return apiTokenString;

    }

}
