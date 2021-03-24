package me.alexjs.coins.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts/{accountId}/transaction")
public class TransactionController implements TransactionApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);


}
