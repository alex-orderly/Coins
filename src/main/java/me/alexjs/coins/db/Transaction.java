package me.alexjs.coins.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne
    @NotNull
    @JsonIgnore
    private Account account;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionType type;

    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;

    Transaction() {
    }

    public Transaction(Account account, String description, TransactionType type, BigDecimal amount) {
        this.account = account;
        this.description = description;
        this.type = type;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public String getDescription() {
        return description;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
