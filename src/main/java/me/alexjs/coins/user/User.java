package me.alexjs.coins.user;

import me.alexjs.coins.account.Account;

import java.util.LinkedList;
import java.util.List;

public class User {

    private final String id;
    private final String userName;
    private final byte[] hashedPassword;
    private final List<Account> accounts;

    private String firstName;
    private String lastName;

    public User(String id, String userName, byte[] hashedPassword, String firstName, String lastName) {
        this.id = id;
        this.userName = userName;
        this.hashedPassword = hashedPassword;
        this.accounts = new LinkedList<>();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void addAccount(String name) {
        accounts.add(new Account(name));
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
