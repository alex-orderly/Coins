package me.alexjs.coins.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @NotNull
    private UUID id;

    @OneToOne
    @NotNull
    private User user;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    @Column(name = "name")
    @NotNull
    private String name;

    Account() {
    }

    public Account(User user, String name) {
        this.user = user;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getName() {
        return name;
    }

}
