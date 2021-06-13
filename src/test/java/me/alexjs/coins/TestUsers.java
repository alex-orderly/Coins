package me.alexjs.coins;

import me.alexjs.coins.api.AccountApi;
import me.alexjs.coins.api.UserApi;
import me.alexjs.coins.api.dto.*;
import me.alexjs.coins.util.PasswordUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestUsers extends CoinsTest {

    private final PasswordUtil passwordUtil;

    @Autowired
    public TestUsers(UserApi userClient, AccountApi accountClient, PasswordUtil passwordUtil) {
        super(userClient, accountClient);
        this.passwordUtil = passwordUtil;
    }

    @Test
    public void testDuplicateUser() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            createUser();
            createUser();
        });
    }


    /* Simple endpoint tests */

    @Test
    public void testCreateUser() {
        CreateUserResponse userResponse = createUser();

        Assertions.assertEquals(USERNAME, userResponse.getUsername());
        Assertions.assertEquals(FIRST_NAME, userResponse.getFirstName());
        Assertions.assertEquals(LAST_NAME, userResponse.getLastName());
    }

    @Test
    public void testGetUser() {
        createUser();
        GetUserResponse user = userClient.getUser(USERNAME);

        Assertions.assertEquals(FIRST_NAME, user.getFirstName());
        Assertions.assertEquals(LAST_NAME, user.getLastName());
        Assertions.assertTrue(passwordUtil.checkPassword(PASSWORD, user.getPasswordHash()));
    }

    @Test
    public void testCreateAccount() {
        createUser();
        GetUserResponse user = userClient.getUser(USERNAME);

        CreateAccountRequest request = new CreateAccountRequest(ACCOUNT_NAME_1);

        CreateAccountResponse account = userClient.createAccount(user.getUsername(), request);
        Assertions.assertEquals(request.getName(), account.getName());
    }

    @Test
    public void testListAccounts() {
        createUser();
        GetUserResponse user = userClient.getUser(USERNAME);

        CreateAccountRequest request1 = new CreateAccountRequest(ACCOUNT_NAME_1);
        CreateAccountRequest request2 = new CreateAccountRequest(ACCOUNT_NAME_2);
        CreateAccountResponse account1 = userClient.createAccount(user.getUsername(), request1);
        CreateAccountResponse account2 = userClient.createAccount(user.getUsername(), request2);

        ListAccountsResponse accounts = userClient.listAccounts(user.getUsername());
        Assertions.assertEquals(2, accounts.getAccounts().size());
        Assertions.assertEquals(account1.getName(), accounts.getAccounts().get(account1.getId()));
        Assertions.assertEquals(account2.getName(), accounts.getAccounts().get(account2.getId()));
    }

    private CreateUserResponse createUser() {
        CreateUserRequest request = new CreateUserRequest(FIRST_NAME, LAST_NAME, PASSWORD);
        return userClient.createUser(USERNAME, request);
    }

}
