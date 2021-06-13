package me.alexjs.coins.api.dto.body;

import java.util.Map;
import java.util.UUID;

public class ListAccountsResponse {

    private Map<UUID, String> accounts;

    public ListAccountsResponse() {
    }

    public ListAccountsResponse(Map<UUID, String> accounts) {
        this.accounts = accounts;
    }

    public Map<UUID, String> getAccounts() {
        return accounts;
    }

}
