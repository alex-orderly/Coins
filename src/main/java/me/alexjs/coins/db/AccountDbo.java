package me.alexjs.coins.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "ACCOUNT", uniqueConstraints = {@UniqueConstraint(columnNames = "shortId")})
public class AccountDbo implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @NotNull
    private UUID id;

    @GeneratedValue(generator = "shortIdGenerator")
    @GenericGenerator(name = "shortIdGenerator", strategy = "me.alexjs.coins.db.ShortIdGenerator")
    @Column(name = "shortId", columnDefinition = "CHAR(7)")
    @NotNull
    private String shortId;

    @Column(name = "name")
    @NotNull
    private String name;

    public AccountDbo(@NotNull String name) {
        this.name = name;
    }

}
