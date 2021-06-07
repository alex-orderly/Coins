package me.alexjs.coins;

import me.alexjs.coins.api.UserApi;
import me.alexjs.coins.api.dto.CreateAccountRequest;
import me.alexjs.coins.api.dto.CreateUserRequest;
import me.alexjs.coins.db.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class TestUsers {

    private static final String username = "ajs1998";
    private static final String firstName = "Alex";
    private static final String lastName = "Sweeney";
    private static final String password = "password1";

    private final UserApi userClient;

    @Autowired
    public TestUsers(UserApi userClient) {
        this.userClient = userClient;
    }

}
