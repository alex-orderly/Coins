package me.alexjs.coins.api;

import me.alexjs.coins.api.util.CoinsException;
import me.alexjs.coins.api.util.CoinsResponse;
import me.alexjs.coins.db.User;
import me.alexjs.coins.db.repository.AccountRepository;
import me.alexjs.coins.db.repository.TransactionRepository;
import me.alexjs.coins.db.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/api")
public class CoinsController implements CoinsApi {

    private static final Logger log = LoggerFactory.getLogger(CoinsController.class);

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public CoinsController(UserRepository userRepository,
                           AccountRepository accountRepository,
                           TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public String createToken(String auth) {
        String encodedToken = auth.split(" ")[1];

        String[] tokens = new String(Base64.getDecoder().decode(encodedToken)).split(":");
        String username = tokens[0];
        String password = tokens[1];

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CoinsException(CoinsResponse.BAD_AUTH);
        }

        String hash = user.getPasswordHash();
        if(!BCrypt.checkpw(password, hash)) {
            throw new CoinsException(CoinsResponse.BAD_AUTH);
        }

        var i = 0;

        return "";
    }

}
