package me.alexjs.coins.db;

import me.alexjs.coins.transaction.Amount;
import me.alexjs.coins.transaction.TransactionType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TRANSACTION")
public class TransactionDbo implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionType type;

    @Column(name = "dollars")
    @NotNull
    private Long dollarAmount;

    @Column(name = "cents")
    @NotNull
    private Long centAmount;

    @Transient
    private Amount amount;

    public TransactionDbo(UUID id, String description, TransactionType type, Amount amount) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.amount = amount;
    }

    @PrePersist
    void fillPersistent() {
        dollarAmount = amount.getDollars();
        centAmount = amount.getCents();
    }

    @PostLoad
    void fillTransient() {
        amount = new Amount(dollarAmount, centAmount);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

}
