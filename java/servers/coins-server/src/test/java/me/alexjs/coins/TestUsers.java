package me.alexjs.coins;

import me.alexjs.coins.api.AccountApi;
import me.alexjs.coins.api.UserApi;
import me.alexjs.coins.api.dto.body.*;
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

        Assertions.assertEquals(USERNAME, userResponse.username());
        Assertions.assertEquals(FIRST_NAME, userResponse.firstName());
        Assertions.assertEquals(LAST_NAME, userResponse.lastName());
    }

    @Test
    public void testGetUser() {
        createUser();
        GetUserResponse user = userClient.getUser(USERNAME);

        Assertions.assertEquals(FIRST_NAME, user.firstName());
        Assertions.assertEquals(LAST_NAME, user.lastName());
        Assertions.assertTrue(passwordUtil.checkPassword(PASSWORD, user.passwordHash()));
    }

    @Test
    public void testCreateAccount() {
        createUser();
        GetUserResponse user = userClient.getUser(USERNAME);

        CreateAccountRequest request = new CreateAccountRequest(ACCOUNT_NAME_1);

        CreateAccountResponse account = userClient.createAccount(user.username(), request);
        Assertions.assertEquals(request.name(), account.name());
    }

    @Test
    public void testListAccounts() {
        createUser();
        GetUserResponse user = userClient.getUser(USERNAME);

        CreateAccountRequest request1 = new CreateAccountRequest(ACCOUNT_NAME_1);
        CreateAccountRequest request2 = new CreateAccountRequest(ACCOUNT_NAME_2);
        CreateAccountResponse account1 = userClient.createAccount(user.username(), request1);
        CreateAccountResponse account2 = userClient.createAccount(user.username(), request2);

        ListAccountsResponse accounts = userClient.listAccounts(user.username());
        Assertions.assertEquals(2, accounts.accounts().size());
        Assertions.assertEquals(account1.name(), accounts.accounts().get(account1.id()));
        Assertions.assertEquals(account2.name(), accounts.accounts().get(account2.id()));
    }

    private CreateUserResponse createUser() {
        CreateUserRequest request = new CreateUserRequest(FIRST_NAME, LAST_NAME, PASSWORD);
        return userClient.createUser(USERNAME, request);
    }

}
