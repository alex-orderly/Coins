package me.alexjs.coins.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.UUID;

public class ListAccountsResponse {

    private Map<UUID, String> accounts;

    public ListAccountsResponse() {
    }

    @JsonCreator
    public ListAccountsResponse(Map<UUID, String> accounts) {
        this.accounts = accounts;
    }

    public Map<UUID, String> getAccounts() {
        return accounts;
    }

}
