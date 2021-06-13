package me.alexjs.coins.util;

import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class PasswordUtil {

    public String hashAndEncode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

}
