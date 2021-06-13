package me.alexjs.coins;

import me.alexjs.coins.api.AccountApi;
import me.alexjs.coins.api.UserApi;
import me.alexjs.coins.api.dto.TransactionDto;
import me.alexjs.coins.api.dto.body.TransactionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestAccounts extends CoinsTest {

    @Autowired
    public TestAccounts(UserApi userClient, AccountApi accountClient) {
        super(userClient, accountClient);
    }

    @Test
    public void testGetBalance() {
        String accountId = createAccount().toString();

        double balance = accountClient.getBalance(accountId).getBalance().doubleValue();
        Assertions.assertEquals(0.0d, balance);
    }

    @Test
    public void testCreateAndListTransactions() {
        String accountId = createAccount().toString();

        accountClient.createDeposit(accountId, new TransactionRequest("Deposit 1", String.valueOf(AMOUNT_1)));
        accountClient.createDeposit(accountId, new TransactionRequest("Deposit 2", String.valueOf(AMOUNT_3)));
        accountClient.createDeposit(accountId, new TransactionRequest("Deposit 3", String.valueOf(AMOUNT_3)));
        accountClient.createWithdrawal(accountId, new TransactionRequest("Withdrawal 1", String.valueOf(AMOUNT_2)));
        accountClient.createWithdrawal(accountId, new TransactionRequest("Withdrawal 2", String.valueOf(AMOUNT_2)));

        double balance = accountClient.getBalance(accountId).getBalance().doubleValue();
        Assertions.assertEquals(AMOUNT_1 + AMOUNT_3 + AMOUNT_3 - AMOUNT_2 - AMOUNT_2, balance);

        List<TransactionDto> transactions = accountClient.listTransactions(accountId).getTransactions();
        Assertions.assertEquals(5, transactions.size());

        Assertions.assertEquals(AMOUNT_1, transactions.get(0).amount().doubleValue());
        Assertions.assertEquals(AMOUNT_3, transactions.get(1).amount().doubleValue());
        Assertions.assertEquals(AMOUNT_3, transactions.get(2).amount().doubleValue());
        Assertions.assertEquals(AMOUNT_2, transactions.get(3).amount().doubleValue());
        Assertions.assertEquals(AMOUNT_2, transactions.get(4).amount().doubleValue());
    }

}
