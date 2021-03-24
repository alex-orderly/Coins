package me.alexjs.coins.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames = "shortId")})
public class UserDbo implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @Access(value = AccessType.FIELD)
    private UUID id;

    @GeneratedValue(generator = "shortIdGenerator")
    @GenericGenerator(name = "shortIdGenerator", strategy = "me.alexjs.coins.db.ShortIdGenerator")
    @Column(name = "shortId", columnDefinition = "CHAR(7)")
    @NotNull
    private String shortId;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "user_name")
    @NotNull
    private String userName;

    @Column(name = "pass_hash")
    @NotNull
    private String passwordHash;

    public UserDbo(String firstName, String lastName, String userName, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passwordHash = passwordHash;
    }

    public UUID getId() {
        return id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

}
