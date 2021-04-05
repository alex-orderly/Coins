package me.alexjs.coins.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "API_TOKEN")
public class ApiToken implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne
    @NotNull
    private User user;

    @Column(name = "token")
    @NotNull
    private String token;

    @Column(name = "expire_at")
    @NotNull
    private Timestamp expireAt;

    ApiToken() {
    }

    public ApiToken(User user, String token, Timestamp expireAt) {
        this.user = user;
        this.token = token;
        this.expireAt = expireAt;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public Timestamp getExpireAt() {
        return expireAt;
    }

}
