package me.alexjs.coins.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne
    @NotNull
    @JsonIgnore
    private Account account;

    @Column
    @NotNull
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionType type;

    @Column
    @NotNull
    private BigDecimal amount;

    @Column
    @NotNull
    private BigDecimal total;

    @Embedded
    private TransactionAudit audit;

    Transaction() {
    }

    public Transaction(Account account, String description, TransactionType type, BigDecimal amount, BigDecimal oldBalance) {
        this.account = account;
        this.description = description;
        this.type = type;
        this.amount = amount.setScale(6, RoundingMode.HALF_UP);
        this.total = oldBalance;
        this.audit = new TransactionAudit();
    }

    /* GETTERS */

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
        return amount.setScale(6, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public TransactionAudit getAudit() {
        return audit;
    }

    /* SETTERS */

    public void updateTotal(TransactionType type, BigDecimal amount) {
        switch (type) {
            case DEPOSIT:
                total = total.add(amount).setScale(6, RoundingMode.HALF_UP);
                break;
            case WITHDRAWAL:
                total = total.subtract(amount).setScale(6, RoundingMode.HALF_UP);
                break;
        }
    }

}
