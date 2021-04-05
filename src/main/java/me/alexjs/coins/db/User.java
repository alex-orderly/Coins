package me.alexjs.coins.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @Access(value = AccessType.FIELD)
    private UUID id;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Account> accounts;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ApiToken> apiTokens;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "user_name", unique = true)
    @NotNull
    private String username;

    @Column(name = "pass_hash")
    @NotNull
    private String passwordHash;

    User() {
    }

    public User(String firstName, String lastName, String username, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public UUID getId() {
        return id;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<ApiToken> getApiTokens() {
        return apiTokens;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

}
