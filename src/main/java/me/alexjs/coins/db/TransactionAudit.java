package me.alexjs.coins.db;

import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.time.Instant;

@Embeddable
public class TransactionAudit {

    private Timestamp createdAt;

    public TransactionAudit() {
        this.createdAt = Timestamp.from(Instant.now());
    }

    public TransactionAudit(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
